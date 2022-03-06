package com.gsg.spring.dto;

import lombok.Getter;

@Getter
public class SummonerDto {
	
	// Encrypted summoner ID
	private String id;
	// Encrypted account ID
	private String accountId;
	// Encrypted PUUID
	private String puuid;
	// Summoner name
	private String name;
	// ID of the summoner icon 
	private int profileIconId;
	// Date summoner was last modified(epoch milliseconds)
	private int revisionDate;
	// Summoner level 
	private int summonerLevel;
	
}
