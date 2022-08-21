<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Registrazione</title>
		<style> @import url("css/style.css"); </style>
	</head>
	<body>
		<div>
			<h1>Registrazione utente</h1>
			<% if(request.getParameter("error") != null && request.getParameter("error") != "") { %>
					<%= request.getParameter("error") %>
			<% } %>
			<div class="form-floating mb-3">
				<form action="RegistrazioneUtenteController" method="POST">
					 <label for="nome">Nome</label> <input type="text" class="form-control" name="nome"> <br>
					 <label for="cognome">Cognome</label> <input type="text" class="form-control" name="cognome"> <br>
					 <label for="email">Email</label> <input type="email" class="form-control" name="email"> <br>
					 <label for="password">Password</label> <input type="password" class="form-control" id="newPassword" name="password"> 
					 <input type="button" id="passButton" value="Visualizza"><br> <br>
					 <input type="submit"/>
				</form> 
			</div>
			<a href="AccessoUtenteController">Accesso Utenti</a>
		</div>
		<script src="js/formCheck.js"></script>
	</body>
</html>