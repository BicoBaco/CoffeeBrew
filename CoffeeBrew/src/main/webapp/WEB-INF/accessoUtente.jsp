<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Accesso Utenti</title>
	</head>
	<body>
		<form action="AccessoUtenteController" method="POST">
			 Email <input type="email" name="email"> <br>
			 Password <input type="password" name="password"> <br>
			 <input type="submit"/> <br> 
		</form>
		<a href="registrazioneUtente.jsp">Registrazione Utenti</a>
	</body>
</html>