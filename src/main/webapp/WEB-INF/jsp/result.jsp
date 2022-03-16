<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${summoner.name}'s Record</title>
		<style>
			body {padding: 1px; margin: 1px;}
			#divHeader {
				background-color: #00D8FF;
				width: 100%;
				text-align: center;

			}
		
		</style>
	</head>
	<body>

		<header>
			<div id="divHeader">
				<img alt="Image error" src="/images/Emblem_${summoner.tier}.png" width="70" height="70"><font size=5> ${summoner.name} <br> </font>
				<b>${summoner.tier} ${summoner.rank}</b>
				${summoner.leaguePoints}LP <br> ${summoner.wins}W
				${summoner.losses}L / Winning rate
				<script type="text/javascript">
					var winningRate;		
					winningRate = Math.round(${summoner.wins} / (${summoner.wins} + ${summoner.losses}) * 100);
					document.write(winningRate, '%<br />');
				</script>
			</div>
		</header>
	</body>
</html>