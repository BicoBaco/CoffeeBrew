<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Registrazione</title>
	</head>
	<body>
		<form action="RegistrazioneUtenteController" method="POST">
			 Nome <input type="text" name="nome">
			 Cognome <input type="text" name="cognome">
			 Email <input type="email" name="email">
			 Password <input type="password" name="password">
			 <input type="submit"/>
		</form>
	</body>
</html>