<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>LOL Record Search</title>
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
				<label for="summoner"><font size=6>LOL Record Search</font></label> <br> 
				<input type="text" id="summoner" name="summoner" required> 
				<input type="submit" value="Search"> 
				<select id="server" name="server">
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
			    <select id="count" name="count">
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="40">40</option>
					<option value="50">50</option>
				</select>
			</form>
		</div>
	</header>
	
	<div id="divResult">
		<font size=5> This summoner is not registered. Please check summoner's name.<br> </font> 
	</div>
</body>
</html>