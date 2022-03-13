package com.gsg.spring.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.gsg.spring.dto.SummonerDto;

@Service
@PropertySource("api.properties")
public class RecordSearchServiceImpl implements RecordSearchService {

	@Value("${api.key}")
	private String apiKey;
	@Value("${common.url}")
	private String commonUrl;
	@Value("${summoner.v4.url}")
	private String summonerV4Url;
	@Value("${league.v4.bysummoner.url}")
	private String leagueV4BySummonerUrl;
	
	@Override
	public SummonerDto getSummonerInfo(String summoner, String server) {
		
		String url = new StringBuilder().append(commonUrl).append(server).append(summonerV4Url).toString();
		DefaultUriBuilderFactory summonerV4Facotry = new DefaultUriBuilderFactory(url);
		WebClient summonerV4Wc = WebClient.builder().uriBuilderFactory(summonerV4Facotry).baseUrl(url).build();
		
		String summonerV4Response= summonerV4Wc.get()
				.uri(uriBuilder -> uriBuilder.path(summoner)
				.queryParam("api_key", apiKey)
				.build())
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		JSONObject jsonObj= new JSONObject(summonerV4Response);
		
		SummonerDto summonerInfo = new SummonerDto();
		summonerInfo.setId(jsonObj.getString("id"));
		summonerInfo.setSummonerLevel(jsonObj.getInt("summonerLevel"));
		summonerInfo.setName(jsonObj.getString("name"));
		
		url = new StringBuilder().append(commonUrl).append(server).append(leagueV4BySummonerUrl).toString();
		DefaultUriBuilderFactory leagueV4BySummonerFacotry = new DefaultUriBuilderFactory(url);
		WebClient leagueV4BySummonerWc = WebClient.builder().uriBuilderFactory(leagueV4BySummonerFacotry).baseUrl(url).build();

		String leagueV4BySummonerResponse = leagueV4BySummonerWc.get()
				.uri(uriBuilder -> uriBuilder.path(summonerInfo.getId())
				.queryParam("api_key", apiKey)
				.build())
				.retrieve()
				.bodyToMono(String.class)
				.block();

		JSONArray jsonArray = new JSONArray(leagueV4BySummonerResponse);
		jsonObj = jsonArray.getJSONObject(0);
	
		summonerInfo.setTier(jsonObj.getString("tier"));
		summonerInfo.setWins(jsonObj.getInt("wins"));
		summonerInfo.setLosses(jsonObj.getInt("losses"));
		summonerInfo.setLeaguePoints(jsonObj.getInt("leaguePoints"));
		summonerInfo.setRank(jsonObj.getString("rank"));
	
		return summonerInfo;
	}

}
