<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Registrazione</title>
	</head>
	<body>
		<% if(request.getParameter("error") != null && request.getParameter("error") != "") { %>
				<%= request.getParameter("error") %>
		<% } %>
		<form action="RegistrazioneUtenteController" method="POST">
			 Nome <input type="text" name="nome"> <br>
			 Cognome <input type="text" name="cognome"> <br>
			 Email <input type="email" name="email"> <br>
			 Password <input type="password" name="password"> <br>
			 <input type="submit"/> <br>	 
		</form>
		<a href="AccessoUtenteController">Accesso Utenti</a>
	</body>
</html>