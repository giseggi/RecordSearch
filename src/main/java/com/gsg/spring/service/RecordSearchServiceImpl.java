package com.gsg.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.gsg.spring.api.ApiKey;
import com.gsg.spring.dto.MatchDto;
import com.gsg.spring.dto.SummonerDto;

@Service
@PropertySource("api.properties")
public class RecordSearchServiceImpl implements RecordSearchService {

	@Value("${common.url}")
	private String commonUrl;
	
	@Value("${summoner.v4.url}")
	private String summonerV4Url;
	
	@Value("${league.v4.bysummoner.url}")
	private String leagueV4BySummonerUrl;
	
	@Value("${match.v5.matches.by.puuid.url}")
	private String matchV5matchesByPuuidUrl;
	
	@Value("${match.v5.matches.url}")
	private String matchV5matchesUrl;
	
	@Autowired
	private final ApiKey API_KEY = new ApiKey();
	
	@Override
	public SummonerDto getSummonerInfo(String summoner, String server) throws WebClientResponseException {

		String apiKey = API_KEY.getApiKey();
		
		String url = new StringBuilder().append(commonUrl).append(server).append(summonerV4Url).toString();
		DefaultUriBuilderFactory summonerV4Facotry = new DefaultUriBuilderFactory(url);
		WebClient summonerV4Wc = WebClient.builder().uriBuilderFactory(summonerV4Facotry).baseUrl(url).build();

		String summonerV4Response = summonerV4Wc.get()
				.uri(uriBuilder -> uriBuilder.path(summoner).queryParam("api_key", apiKey).build()).retrieve()
				.bodyToMono(String.class).block();

		JSONObject jsonObj = new JSONObject(summonerV4Response);

		SummonerDto summonerInfo = new SummonerDto();
		summonerInfo.setId(jsonObj.getString("id"));
		summonerInfo.setSummonerLevel(jsonObj.getInt("summonerLevel"));
		summonerInfo.setName(jsonObj.getString("name"));
		summonerInfo.setPuuid(jsonObj.getString("puuid"));

		url = new StringBuilder().append(commonUrl).append(server).append(leagueV4BySummonerUrl).toString();
		DefaultUriBuilderFactory leagueV4BySummonerFacotry = new DefaultUriBuilderFactory(url);
		WebClient leagueV4BySummonerWc = WebClient.builder().uriBuilderFactory(leagueV4BySummonerFacotry).baseUrl(url)
				.build();

		String leagueV4BySummonerResponse = leagueV4BySummonerWc.get()
				.uri(uriBuilder -> uriBuilder.path(summonerInfo.getId()).queryParam("api_key", apiKey).build())
				.retrieve().bodyToMono(String.class).block();

		JSONArray jsonArray = new JSONArray(leagueV4BySummonerResponse);
		

		if (jsonArray.isEmpty()) {
			summonerInfo.setTier("Unranked");
			summonerInfo.setRank("");
		} else {
			jsonObj = jsonArray.getJSONObject(0);
			summonerInfo.setTier(jsonObj.getString("tier"));
			summonerInfo.setWins(jsonObj.getInt("wins"));
			summonerInfo.setLosses(jsonObj.getInt("losses"));
			summonerInfo.setLeaguePoints(jsonObj.getInt("leaguePoints"));

			if (jsonObj.getString("tier").equals("CHALLENGER") || jsonObj.getString("tier").equals("GRANDMASTER")
					|| jsonObj.getString("tier").equals("MASTER")) {
				summonerInfo.setRank("");
			} else {
				switch (jsonObj.getString("rank")) {
				case "I":
					summonerInfo.setRank("1");
					break;

				case "II":
					summonerInfo.setRank("2");
					break;

				case "III":
					summonerInfo.setRank("3");
					break;

				case "IV":
					summonerInfo.setRank("4");
					break;
				}
			}
		}

		return summonerInfo;
	}

	@Override
	public List<String> getMatchId(String puuid, String server) throws Exception {
		String apiKey = API_KEY.getApiKey();
	
		String region = getRegion(server);
		
		String url = new StringBuilder().append(commonUrl).append(region).append(matchV5matchesByPuuidUrl).toString();
		
		DefaultUriBuilderFactory matchesFacotry = new DefaultUriBuilderFactory(url);
		WebClient matchesWc = WebClient.builder().uriBuilderFactory(matchesFacotry).baseUrl(url).build();

		String matchV5matchesByPuuidResponse = matchesWc.get()
				.uri(uriBuilder -> uriBuilder.path(puuid + "/ids").queryParam("start", "0")
						.queryParam("count", "10").queryParam("api_key", apiKey).build()).retrieve()
				.bodyToMono(String.class).block();
		
		JSONArray jsonArray = new JSONArray(matchV5matchesByPuuidResponse);
		
		List<String> matchesId = new ArrayList<String>();
		for(int i = 0; i < jsonArray.length(); i++) {
			matchesId.add(jsonArray.getString(i));
		}
		return matchesId;
	}

	@Override
	public MatchDto getMatchInfo(String matchId, String server, String summoer) throws Exception {
		String apiKey = API_KEY.getApiKey();
		
		String region = getRegion(server);
		
		String url = new StringBuilder().append(commonUrl).append(region).append(matchV5matchesUrl).toString();
		
		DefaultUriBuilderFactory matchesFacotry = new DefaultUriBuilderFactory(url);
		WebClient matchesWc = WebClient.builder().uriBuilderFactory(matchesFacotry).baseUrl(url).build();
		
		String matchesResponse = matchesWc.get()
				.uri(uriBuilder -> uriBuilder.path(matchId).queryParam("api_key", apiKey).build()).retrieve()
				.bodyToMono(String.class).block();
				
		JSONObject jsonObj = new JSONObject(matchesResponse);
		
		JSONObject jsonGameInfo = jsonObj.getJSONObject("info");
		
		JSONArray jsonParticipantsArray = jsonGameInfo.getJSONArray("participants");
		
		MatchDto matchInfo = new MatchDto();
		
		List<String> summonerNames = new ArrayList<String>();
		List<Integer> championIds = new ArrayList<Integer>();
		List<String> championNames = new ArrayList<String>();
		int blueTeamTotalKills = 0;
		int redTeamTotalKills = 0;

		for(int i = 0; i < jsonParticipantsArray.length(); i++) {
			JSONObject jsonParticipant = jsonParticipantsArray.getJSONObject(i);
			
			//calculate total kills		
			if(jsonParticipant.getInt("teamId") == 100) {
				blueTeamTotalKills += jsonParticipant.getInt("kills");
			} else {
				redTeamTotalKills += jsonParticipant.getInt("kills");
			}
		}
		
		for(int i = 0; i < jsonParticipantsArray.length(); i++) {
			JSONObject jsonParticipant = jsonParticipantsArray.getJSONObject(i);
					
			if(StringUtils.equals(jsonParticipant.getString("summonerName"), summoer)) {
				matchInfo.setChampionId(jsonParticipant.getInt("championId"));
				matchInfo.setChampionName(jsonParticipant.getString("championName"));
				matchInfo.setChampionLevel(jsonParticipant.getInt("champLevel"));			
				matchInfo.setSummonerSpell1(jsonParticipant.getInt("summoner1Id"));
				matchInfo.setSummonerSpell2(jsonParticipant.getInt("summoner2Id"));
				matchInfo.setKda(jsonParticipant.getJSONObject("challenges").getDouble("kda"));
				matchInfo.setMainRune(jsonParticipant.getJSONObject("perks").getJSONArray("styles")
						.getJSONObject(0).getJSONArray("selections").getJSONObject(0).getInt("perk"));
				matchInfo.setAuxiliaryRune(jsonParticipant.getJSONObject("perks").getJSONArray("styles")
						.getJSONObject(1).getInt("style"));
				
				List<Integer> items = new ArrayList<Integer>();
				for(int j = 0; j < 7; j++) {
					items.add(jsonParticipant.getInt("item" + j));
				}
				matchInfo.setItems(items);
				
				matchInfo.setTotalMinionsKilled(jsonParticipant.getInt("totalMinionsKilled"));
				matchInfo.setNeutralMinionsKilled(jsonParticipant.getInt("neutralMinionsKilled"));
				
				int ka = jsonParticipant.getInt("kills") + jsonParticipant.getInt("assists");
				double killInvolvementRate;
				if(jsonParticipant.getInt("teamId") == 100) {
					killInvolvementRate = (ka * 1.0) / blueTeamTotalKills;
				} else {
					killInvolvementRate = (ka * 1.0) / redTeamTotalKills;
				}
				matchInfo.setKillInvolvementRate(killInvolvementRate);
				
				matchInfo.setGameDuration(jsonGameInfo.getInt("gameDuration"));
				matchInfo.setGameStartTimestamp(jsonGameInfo.getLong("gameStartTimestamp"));
				matchInfo.setQueueId(jsonGameInfo.getInt("queueId"));
				matchInfo.setVisionWardsBoughtInGame(jsonParticipant.getInt("visionWardsBoughtInGame"));
				
				summonerNames.add(jsonParticipant.getString("summonerName"));
				championIds.add(jsonParticipant.getInt("championId"));
				
			} else {
				summonerNames.add(jsonParticipant.getString("summonerName"));
				championIds.add(jsonParticipant.getInt("championId"));
				championNames.add(jsonParticipant.getString("championName"));
			}
		}
		
		matchInfo.setSummonerNames(summonerNames);
		matchInfo.setChampionIds(championIds);
		
		return matchInfo;
	}
	
	private static String getRegion(String server) throws Exception {
		String region;
		switch(server) {
			case "na1":
			case "br1":
			case "la1":
			case "la2":
			case "oc1":	
				region = "americas";
				break;
				
			case "jp1":
			case "kr":
				region = "asia";
				break;
			
			case "euw1":
			case "eun1":
			case "tr1":
			case "ru":
				region = "asia";
				break;
			
			default :
				throw new Exception("invalid server");
			
		}
		
		return region;
	}

}
