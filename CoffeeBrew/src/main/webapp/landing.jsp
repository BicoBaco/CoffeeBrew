<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="css/style.css">
		<title>CoffeeBrew</title>
	</head>
	<body id="landing_background">
		<%@include file="includes/navbar.jsp"%>
		<div class="d-flex flex-column min-vh-100 justify-content-center align-items-center text-light ">
			<h1 class="display-1">CoffeeBrew</h1>
			<br>
		    <p class="fs-3">Sistema innovativo per la gestione di distributori automatici in maniera distribuita</p>
		    <div class="card bg-dark" style="width: 18rem;">
				<div class="card-body">
					<a class="btn btn-success w-100 mb-2"	href="AccessoUtenteController">Accesso Utenti</a>
					<a class="btn btn-success w-100 mb-2"	href="RegistrazioneUtenteController">Registrati</a>
					<a class="btn btn-success w-100 mb-2"	href="AccessoTecnicoController">Accesso Tecnici</a>
					<a class="btn btn-success w-100"		href="AccessoAmministratoreController">Accesso Amministratori</a>
				</div>
			</div>    
		</div>
	</body>
</html>