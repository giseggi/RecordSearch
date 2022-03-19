package com.gsg.spring.service;

import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.gsg.spring.dto.SummonerDto;

public interface RecordSearchService {
	public SummonerDto getSummonerInfo(String summoner, String server) throws WebClientResponseException;
}
