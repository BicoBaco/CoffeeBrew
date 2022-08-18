<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<button id="poll" type="button">Polling</button>
		<input type="number" id="idDistributore" name="idDistributore">
		<div id="demo"></div>
		
		<div id="interfaccia" hidden>
			<form action="DistributoreAutomaticoController" method="post">
				<input type="hidden" value="20">
				<input id="acquistaButton" type="submit" value="Acquista">
			</form>
		</div>
		
		<script src="polling.js"></script>
	</body>
</html>