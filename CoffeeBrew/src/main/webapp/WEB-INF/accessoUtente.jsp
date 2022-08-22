<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Accesso Utenti</title>
		<style> @import url("css/style.css");</style>
	</head>
	<body>
		<div class="container bg-white">
			<h1>Accesso utente</h1>
			<% if(request.getParameter("error") != null && request.getParameter("error") != "") { %>
					<%= request.getParameter("error") %>
			<% } %>
			<form action="AccessoUtenteController" method="POST">
				 Email <input type="email" name="email"> <br>
				 Password <input type="password" id="password" name="password"> 
				  <input type="button" id="passButton" value="Visualizza"><br>
				 <input type="submit"/> <br> 
			</form>
			<a href="RegistrazioneUtenteController">Registrazione Utenti</a>
		</div>
	</body>
	<script src="js/formCheck.js"></script>
</html>