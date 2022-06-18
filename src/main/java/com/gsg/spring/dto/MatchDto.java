package com.gsg.spring.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchDto {

	// Match Id
	String matchId;
	
	// Champion Id
	int championId;	
	
	// Champion Name
	String championName;
	
	// Champion Level
	int championLevel;
	
	// Summoner Spell1
	int summonerSpell1;

	// Summoner Spell1 Id
	String summonerSpellId1;
	
	// Summoner Spell2
	int summonerSpell2;
	
	// Summoner Spell2 Id
	String summonerSpellId2;
	
	// kill death assist
	int kills;
	
	int deaths;
	
	int assists;
	
	// 1:none, 2:double kill, 3:triple kill, 4:quadra kill, 5:penta kill
	int multipleKillsCode;
	
	// Kill + Assist / Death
	String Kda;
	
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
	
	// Game end timestamp
	long gameEndTimestamp;
	
	// Difference from the current time
	String daysAgo;
	
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
	
	// 1:Victory 2:Defeat 3:Remake
	int resultCode;
	
	// Main rune icon info
	String mainRuneIconInfo;
	
	// Auxiliary rune icon info
	String auxiliaryRuneconInfo;
	
	// Latest version
	String latestVersion;
	
}
