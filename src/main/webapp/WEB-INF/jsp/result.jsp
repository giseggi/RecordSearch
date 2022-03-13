<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${summoner.name}'s Record</title>
	</head>
	<body>

		${summoner.name}'s Record <br>
		<b>${summoner.tier}</b> ${summoner.leaguePoints}LP <br>
		${summoner.wins}W ${summoner.losses}L / Winning rate 
	<script type="text/javascript">
		var winningRate;
		
		winningRate = Math.round(${summoner.wins} / (${summoner.wins} + ${summoner.losses}) * 100);
		document.write(winningRate, '%<br />');
	</script>
	</body>
</html>