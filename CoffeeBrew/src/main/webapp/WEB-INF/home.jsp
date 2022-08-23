<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<style> @import url("css/style.css"); </style>
		<title>CoffeeBrew - Home Utente</title>
	</head>
	<body>
		<div class="container bg-white">
			<h1>Ciao ${utente.getNome()}${tecnico.getNome()}${amministratore.getNome()}</h1>
			<a href="CarteDiCreditoController">Gestione Carte di Credito</a> <br>
			<a href="LogoutController">Logout</a> <br>
			<form action="ConnessioneDistributoreAutomaticoController" method="get">
				Connetti al distributore: <input type="number" name="idDistributore">
				<input type="submit">
			</form>
		</div>
	</body>
</html>