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
div {
	text-align: center;
	background-color: white;
	margin-left: 30%;
	margin-top: 20%;
	margin-right: 30%;
}
</style>
<title>CoffeeBrew</title>
</head>
<body>
	<div></div>
	<div>
		<h1>Ciao ${utente.getNome()}${tecnico.getNome()}${amministratore.getNome()}</h1>
		<a href="RegistrazioneUtenteController">Registrazione Utenti</a> <br>
		<a href="AccessoUtenteController">Accesso Utenti</a> <br>
		<a href="AccessoTecnicoController">Accesso Tecnici</a> <br>
		<a href="AccessoAmministratoreController">Accesso Amministratore</a> <br>
		<a href="PannelloDiControlloAmministratoreController">Pannello di controllo Amministratore</a> <br>
		<a href="CarteDiCreditoController">Gestione Carte di Credito</a> <br>
		<a href="LogoutUtenteController">Logout</a> <br>
		<form action="ConnessioneDistributoreAutomaticoController" method="get">
			Connetti al distributore: <input type="number" name="idDistributore">
			<input type="submit">
		</form>
	</div>
	<div></div>
</body>
</html>