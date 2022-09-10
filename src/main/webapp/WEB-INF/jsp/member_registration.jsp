<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>LOL Record Search</title>
		<link rel="stylesheet" type="text/css" id="style" href = "css/light.css">
	</head>
<body>
	<div id="dark_mode_container"></div>
	<header>
		<div id="divHeader">
			<form action="/none">
				<label for="summoner"><font size=6>Member Registration</font></label><br> 
				ID: <input type="text" id="memberId" name="memberId"  required> <br>
				Password: <input type="password" id="password" name="password" required> <br> 
				Nickname: <input type="text" id="nickname" name="nickname" required> <br>
				Mail Address(※): <input type="text" id="mailAddress" name="mailAddress" > <br>
				Note(※): <input type="text" id="note" name="note" > <br>
				<input type="submit" value="Registration"> 

				<input type="hidden" id="darkFlg" name="darkFlg" value="">
			</form>
		</div>
	</header>
	<script src="https://unpkg.com/react@17/umd/react.development.js" crossorigin></script>
  	<script src="https://unpkg.com/react-dom@17/umd/react-dom.development.js" crossorigin></script>
  	<script src="dark_mode_button.js"></script>
</body>
</html>