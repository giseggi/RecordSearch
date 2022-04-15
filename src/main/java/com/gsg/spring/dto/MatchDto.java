package com.gsg.spring.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchDto {

	// Champion Id
	int championId;	
	
	// Champion Name
	String championName;
	
	// Champion Level
	int championLevel;
	
	// Summoner Spell1
	int summonerSpell1;
	
	// Summoner Spell2
	int summonerSpell2;
	
	// Kill + Assist / Death
	double Kda;
	
	// Main rune
	int mainRune;
	
	// Auxiliary rune
	int auxiliaryRune;
	
	// Items
	List<Integer> items;
	
	// Total minions killed
	int totalMinionsKilled;
	
	// Neutral minions killed
	int neutralMinionsKilled;
	
	// Kill involvement rate
	double killInvolvementRate;
	
	// Game duration(sec)
	int gameDuration;
	
	// Game start timestamp
	long gameStartTimestamp;
	
	// Queue id
	int queueId;
	
	// Queue description
	String queueDescription;
	
	// Vision wards bought in game
	int visionWardsBoughtInGame;
	
	// Summoners name in game
	List<String> summonerNames;
	
	// Champions id in game
	List<Integer> championIds;
	
	// Champions name in game
	List<String> championNames;
	
	// Victory flag
	boolean victotryFlag;
	
}
