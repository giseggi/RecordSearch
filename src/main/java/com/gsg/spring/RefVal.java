package com.gsg.spring;

public class RefVal {
	
	// Version info URL
	public static final String VERSION_INFO_URL = "https://ddragon.leagueoflegends.com/api/versions.json";
	
	// Queue info URL
	public static final String QUEUE_INFO_URL = "https://static.developer.riotgames.com/docs/lol/queues.json";
	
	// Rune info URL
	public static final String RUNE_INFO_URL = "https://ddragon.leagueoflegends.com/cdn/**version**/data/en_US/runesReforged.json";
	
	// Summoner spell info URL
	public static final String SUMMONER_SPELL_INFO_URL = "http://ddragon.leagueoflegends.com/cdn/**version**/data/en_US/summoner.json";
	
	// One day to second
	public static final long ONE_DAY_SEC = 86400L;
		
	// One hour to second
	public static final long ONE_HOUR_SEC = 3600L;
			
	// Ten minutes to second
	public static final long TEN_MIN_SEC = 600;
	
	// One minutes to second
	public static final long ONE_MIN_SEC = 60;
	
	// Result code 1:Victory
	public static final int RESULT_CODE_VICTORY = 1;
	
	// Result code 2:Defeat
	public static final int RESULT_CODE_DEFEAT = 2;
		
	// Result code 3:Remake
	public static final int RESULT_CODE_REMAKE = 3;
	
	// Multiple kills code 1:none
	public static final int MULTIPLE_KILLS_CODE_NONE = 1;
	
	// Multiple kills code 2:double kill
	public static final int MULTIPLE_KILLS_CODE_DOUBLE_KILL = 2;
		
	// Multiple kills code 3:triple kill
	public static final int MULTIPLE_KILLS_CODE_TRIPLE_KILL = 3;
		
	// Multiple kills code 4:quadra kill
	public static final int MULTIPLE_KILLS_CODE_QUADRA_KILL = 4;
	
	// Multiple kills code 5:penta kill
	public static final int MULTIPLE_KILLS_CODE_PENTA_KILL = 5;
	
	// QueueId 420:5v5 Ranked Solo games
	public static final int QUEUE_ID_5V5_RANKED_SOLO_GAME = 420;
	
	// Match info start index
	public static final int MATCH_INFOS_START_INDEX = 0;
		
}
