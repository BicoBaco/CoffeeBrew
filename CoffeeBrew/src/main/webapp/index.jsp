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
	<h1>Ciao ${utente.getNome()}${tecnico.getNome()}${amministratore.getNome()}</h1>
	<a href="registrazioneUtente.jsp">Registrazione Utenti</a> <br>
	<a href="accessoUtente.jsp">Accesso Utenti</a> <br>
	<a href="accessoTecnico.jsp">Accesso Tecnici</a> <br>
	<a href="accessoAmministratore.jsp">Accesso Amministratore</a> <br>
	<a href="CarteDiCreditoController">Gestione Carte di Credito</a> <br>
	<form action="LogoutUtenteController" method="post">
   		<input type="submit" value="Logout" />
	</form>
	<form>
	
	</form>
</body>
</html>