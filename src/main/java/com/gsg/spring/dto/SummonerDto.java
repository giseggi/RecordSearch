package com.gsg.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	// Summoner level 
	private int summonerLevel;
	// Queue type
	private String queueType;
	// Tier
	private String tier;
	// League points
	private int leaguePoints;
	// Winning team on Summoners Rift.
	private int wins;
	// Losing team on Summoners Rift.
	private int losses;
	
}
