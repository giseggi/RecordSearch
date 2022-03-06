package com.gsg.spring.service;

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
	@Value("${summoner.v4.url1}")
	private String summonerV4Url1;
	@Value("${summoner.v4.url2}")
	private String summonerV4Url2;
	
	@Override
	public SummonerDto getSummonerInfo(String summoner, String server) {
		
		String url = new StringBuilder().append(summonerV4Url1).append(server).append(summonerV4Url2).toString();
		DefaultUriBuilderFactory facotry = new DefaultUriBuilderFactory(url);
		WebClient wc = WebClient.builder().uriBuilderFactory(facotry).baseUrl(url).build();
		
		String response = wc.get()
				.uri(uriBuilder -> uriBuilder.path(summoner)
				.queryParam("api_key", apiKey)
				.build())
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		return null;
	}

}
