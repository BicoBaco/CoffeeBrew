<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="css/style.css">
		<title>CoffeeBrew - Registrazione</title>
	</head>
	<body>
		<main>
			<%@include file="/includes/navbar.jsp"%>
			<div class="container-fluid row px-0 vh-100">
				<div class="card mx-auto my-auto" style="width: 28rem;">
					<div class="card-body" id="cardForm">
						<h5 class="card-title fw-bold">Registrazione</h5>
						<p class="card-text">
						<% if(request.getParameter("error") != null && request.getParameter("error") != "") { %>
							<%= request.getParameter("error") %>
						<% } %>
						<form id="formRegistrazioneUtente" action="RegistrazioneUtenteController" method="POST">
							<div class="mb-3">
								<label for="nome" class="form-label">Nome</label> 
								<input type="text" class="form-control" name="nome">
							</div>
							<div class="mb-3">
								<label for="cognome" class="form-label">Cognome</label> 
								<input type="text" class="form-control" name="cognome">
							</div>
							<div class="mb-3">
								<label for="email" class="form-label">Indirizzo email</label> 
								<input type="email" class="form-control" name="email">
							</div>
							<div class="mb-3">
								<label for="password" class="form-label">Password</label>
								<input type="password" class="form-control" id="newPassword" name="password">
								<input type="button" id="passButton" value="Visualizza" class="w-50 btn btn-success text-center">
							</div>
							<div class="text-center w-100">
								<button type="submit" class="w-50 btn btn-success text-center">Accedi</button>
							</div>
						</form>
						</p>
					</div>
					<div class="card-body border-top border-3">
						<a class="btn btn-primary w-100 mb-1" href="RegistrazioneUtenteController">Registrazione Utenti</a> <br>
						<a class="btn btn-primary w-100 mb-1" href="AccessoUtenteController">Accesso Utenti</a>
						<a class="btn btn-primary w-100 mb-1" href="AccessoTecnicoController">Accesso Tecnici</a>
						<a class="btn btn-primary w-100 mb-1" href="AccessoAmministratoreController">Accesso Amministratore</a>
					</div>
				</div>
			</div>
		</main>
		<script src="js/formCheck.js"></script>
	</body>
</html>