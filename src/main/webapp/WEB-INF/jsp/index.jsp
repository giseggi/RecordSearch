<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>LOL Record Search</title>
	</head>
	<body>
		<form action="/result">
			<label for="summoner">LOL Record Search</label><br>
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
		</form>
	</body>
</html>