<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>CoffeeBrew - Accesso Utenti</title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%@include file="/includes/navbar.jsp"%>
		<div class="container-fluid row px-0 vh-100">
			<div class="card mx-auto my-auto" style="width: 28rem;">
				<div class="card-body" id="cardForm">
					<h5 class="card-title fw-bold">Accesso Utenti</h5>
					<p class="card-text">
					<% if(request.getParameter("error") != null && request.getParameter("error") != "") { %>
						<div class="alert alert-danger" role="alert">
						  <%= request.getParameter("error") %>
						</div>
					<% } %>
					<form action="AccessoUtenteController" method="POST">
						<div class="form-floating mb-3">
							<input id="email" type="email" class="form-control" name="email" placeholder="Indirizzo email" required>
							<label for="email" class="form-label">Indirizzo email</label> 
						</div>
						<div class="input-group form-floating mb-3">
							<input type="password" class="form-control" id="password" name="password" placeholder="Password" aria-describedby="visualizzaBtn" required>
							<button id="visualizzaBtn" type="button" class="btn btn-outline-secondary">Visualizza</button>
							<label for="password" class="form-label">Password</label>
						</div>
						<div class="text-center w-100">
							<button type="submit" class="w-50 btn btn-success text-center">Accedi</button>
						</div>
					</form>
					<br>
					<div class="text-center">
						Non sei registrato? <a href="RegistrazioneUtenteController">Registrati</a>
					</div>
					</p>
				</div>
				<div class="card-body border-top border-3">
					<a class="btn btn-primary w-100 mb-1" href="landing.jsp">Home</a>
				</div>
			</div>
		</div>
		<script src="js/formUtilities.js"></script>
	</body>
</html>