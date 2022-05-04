<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${summoner.name}'s Record</title>
		<style>
			body {padding: 1px; margin: 1px;}
			
			#divResult {
				background-color: #00D8FF;
				width: 60%;
				text-align: center;
 				margin: 10px auto;
			}
			
			#divHeader {
				width: 100%;
				text-align: center;				
			}
			
			#multipleKill {
				background-color: #CD5C5C;
				border-radius: 40px;
			}
			
			table {
				width: 60%;
				margin: auto;
				border-collapse: separate;
				border-spacing: 0 10px;
				border:none;					
			}
						
			.Victory {background-color: #81BEF7;}
			.Defeat {background-color: #F5A9BC;}
			.Remake {background-color: #A9A9A9;}				
		</style>
		
			<script type="text/javascript">
				var winningRate;		
				winningRate = Math.round(${summoner.wins} / (${summoner.wins} + ${summoner.losses}) * 100);
				
				function getResult(resultCode) {
					const result = {
						1: 'Victory',
					    2: 'Defeat',
					    3: 'Remake',
					};
					return result[resultCode] || 'result code error';
				}
				
				function getMultipleKill(multipleKillCode) {
					const multipleKill = {
						1: 'none',
					    2: 'Double Kill',
					    3: 'Triple Kill',
					    4: 'Quadra Kill',
					    5: 'Penta Kill',
					};
					return multipleKill[multipleKillCode] || 'multiple kill code error';
				}
				
				function getDurationToMMSS(gameDuration) {
					const min = parseInt(gameDuration / 60);
					const second = gameDuration % 60;
					
					return min.toString().concat('m ', second, 's');
				}
				
				
			</script>
		

	</head>
	<body>

	<header>
		<div id="divHeader">
			<form action="/result">
				<label for="summoner"><font size=6>LOL Record Search</font></label><br> <input
					type="text" id="summoner" name="summoner" required> <input
					type="submit" value="Search"> <select id="server"
					name="server">
					<option value="kr">KR</option>
					<option value="jp1">JP</option>
					<option value="na1">NA</option>
					<option value="euw1">EUW</option>
					<option value="eun1">EUNE</option>
					<option value="oc1">OCE</option>
					<option value="br1">BR</option>
					<option value="la2">LAS</option>
					<option value="la1">LAN</option>
					<option value="ru">RU</option>
					<option value="tr1">TR</option>
				</select>
			</form>
		</div>
	</header>

	<div id="divResult">
		<img alt="Image error" src="/images/Emblem_${summoner.tier}.png" width="60" height="60"><font size=5> ${summoner.name} <br>
		</font> <b>${summoner.tier} ${summoner.rank}</b>
		<c:choose>
			<c:when test="${summoner.tier eq 'Unranked'}"></c:when>
			<c:otherwise>
				${summoner.leaguePoints}LP <br> ${summoner.wins}W
				${summoner.losses}L / 
				<script type="text/javascript">
					var winningRate;		
					winningRate = Math.round(${summoner.wins} / (${summoner.wins} + ${summoner.losses}) * 100);
					document.write(winningRate, '%<br>');
				</script>
			</c:otherwise>
		</c:choose>
	</div>
	<div>
	<table>
		<c:forEach var="matchInfo" items="${matchInfoList}">
			<tr id="${matchInfo.matchId}">
				<td width=150px align="center" ><font size="1">${matchInfo.queueDescription}</font> <br> ${matchInfo.daysAgo} ago <br>
					<script type="text/javascript">
						var result = getResult(${matchInfo.resultCode});
						
						if(result == "Victory") {
							document.write("<b><font color=\"#2E64FE\">");
							document.write(result);
							document.write("</font></b>", '<br>');
						} else if(result == "Defeat") {
							document.write("<b><font color=\"#FE2E2E\">");
							document.write(result);
							document.write("</font></b>", '<br>');
						} else {
							document.write(result, '<br>');	
						}
												
						var duration = getDurationToMMSS(${matchInfo.gameDuration});
						document.write(duration, '<br>');
						
						document.getElementById('${matchInfo.matchId}').setAttribute('class', result );
					</script>
				</td>
				<td width="150px">
					<div style="width:65px; height:70px; float:left;">
						<img alt="Image error" src="http://ddragon.leagueoflegends.com/cdn/12.6.1/img/champion/${matchInfo.championName}.png" width="65" height="65">
					</div>
					<div style="width:40px; height:70px; float:left; padding-left:5px">
						<img alt="Image error" src="http://ddragon.leagueoflegends.com/cdn/12.7.1/img/spell/${matchInfo.summonerSpellId1}.png" width="30" height="30">
						<img alt="Image error" src="http://ddragon.leagueoflegends.com/cdn/12.7.1/img/spell/${matchInfo.summonerSpellId2}.png" width="30" height="30">
					</div>
					<div style="width:40px; height:70px; float:left;">
						<img alt="Image error" src="https://ddragon.leagueoflegends.com/cdn/img/${matchInfo.mainRuneIconInfo}" width="30" height="30">
						<img alt="Image error" src="https://ddragon.leagueoflegends.com/cdn/img/${matchInfo.auxiliaryRuneconInfo}" width="27" height="27">
					</div>
					${matchInfo.championName}
				</td>
				<td width="100px" style="text-align: center;">
					${matchInfo.kills} / ${matchInfo.deaths} / ${matchInfo.assists} <br> ${matchInfo.kda} KDA <br>
					<div id="multipleKill">
						<font size="1">
							<script type="text/javascript">
								var multipleKill = getMultipleKill(${matchInfo.multipleKillsCode});	
								if(multipleKill != "none") {
									document.write(multipleKill, '<br>');
								}			
							</script>
						</font>
					</div>
				</td>
				<td width="120px">
					Level ${matchInfo.championLevel}
					<div>
						<script type="text/javascript">
							var totalMinion = ${matchInfo.totalMinionsKilled} + ${matchInfo.neutralMinionsKilled};	
							var minionPerMinute = Math.round((totalMinion / (${matchInfo.gameDuration} / 60)) * 10) / 10;
							var PKill = Math.round(${matchInfo.killInvolvementRate} * 100);
							document.write(totalMinion + " (" + minionPerMinute + ") CS", '<br>');
							document.write("P/Kill " + PKill + "%", '<br>');
						</script>
					</div>
				</td>
				<td width="280px">
					<c:forEach var="item" items="${matchInfo.items}">
						<c:choose>					
							<c:when test="${item ne 0 }">	
								<div style="float:left; display:inline-block;">
									<img style="width:40px; height:40px; border-radius: 10px" alt="Image error" src="http://ddragon.leagueoflegends.com/cdn/12.7.1/img/item/${item}.png" width="65" height="65">
								</div>
							</c:when>
							<c:otherwise>
								<div style="width:40px; height:40px; float:left; border-radius: 10px; display:inline-block; background-color: #A9E2F3"></div>								
							</c:otherwise>
						</c:choose>
					</c:forEach>	
				</td>				
				<td>
					<script type="text/javascript">
							var summonerNameList = new Array();
							var index = 0;
					</script>
					<c:forEach var="summonerName" items="${matchInfo.summonerNames}">
						<script type="text/javascript">
							summonerNameList.push("${summonerName}");
						</script>
					</c:forEach>
					<c:forEach var="championName" items="${matchInfo.championNames}">
						<div style="float:left; display:inline-block; height:20px;">
							<img alt="Image error" src="http://ddragon.leagueoflegends.com/cdn/12.6.1/img/champion/${championName}.png" width="20" height="20">
							<script type="text/javascript">
								document.write(summonerNameList[index]);
								index = index + 1;
							</script>						
						</div>

					</c:forEach>
				</td>				
			</tr>
			
		</c:forEach>
	</table>
	</div>
</body>
</html>