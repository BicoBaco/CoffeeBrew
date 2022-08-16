<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
body {
  background-image: url('https://i.ytimg.com/vi/kkBgqTXLgTw/maxresdefault.jpg');
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 100% 100%;
}
</style>
<title>CoffeeBrew</title>
</head>
<body>
	<h1>Ciao ${utente.getNome()}</h1>
	<a href="registrazione.jsp">Registrazione Utenti</a> <br>
	<a href="accesso.jsp">Accesso Utenti</a>
	<form action="LogoutUtenteController" method="post">
   		<input type="submit" value="Logout" />
	</form>
</body>
</html>