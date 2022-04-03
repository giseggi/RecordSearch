package com.gsg.spring.service;

import java.util.List;

import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.gsg.spring.dto.MatchDto;
import com.gsg.spring.dto.SummonerDto;

public interface RecordSearchService {
	public SummonerDto getSummonerInfo(String summoner, String server) throws WebClientResponseException;
	
	public List<String> getMatchId(String puuid, String server) throws Exception;
	
	public MatchDto getMatchInfo(String matchId, String server, String summoer) throws Exception;
}
