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
				width: 100%;
				text-align: center;
			}
			
			#divHeader {
				width: 100%;
				text-align: center;
			}	
		</style>
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
				${summoner.losses}L / Winning rate
				<script type="text/javascript">
					var winningRate;		
					winningRate = Math.round(${summoner.wins} / (${summoner.wins} + ${summoner.losses}) * 100);
					document.write(winningRate, '%<br />');
				</script>
			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>