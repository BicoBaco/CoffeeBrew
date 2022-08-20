<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Pannello di controllo Tecnico</title>
	<style> @import url("css/style.css"); </style>
</head>
<body>
	<% if(request.getParameter("error") != null && request.getParameter("error") != "") { %>
			<%= request.getParameter("error") %>
	<% } %>
	
	<form action="ConnessioneDistributoreAutomaticoController" method="get">
		Connetti al distributore: <input type="number" name="idDistributore">
		<input type="submit">
	</form>
</body>
</html>