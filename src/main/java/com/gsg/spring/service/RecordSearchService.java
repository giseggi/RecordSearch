package com.gsg.spring.service;

import com.gsg.spring.dto.SummonerDto;

public interface RecordSearchService {
	public SummonerDto getSummonerInfo(String summoner, String server);
}
