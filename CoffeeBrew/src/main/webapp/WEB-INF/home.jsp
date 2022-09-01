<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>CoffeeBrew - Home Utenti</title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<body>
		<%@include file="/includes/navbar.jsp"%>
		<div class="container-fluid row px-0 vh-100">
			<div class="card mx-auto my-auto" style="width: 28rem;">
				<div class="card-body" id="cardForm">
					<h5 class="card-title fw-bold">Home</h5>
					<p class="card-text">
					<% if(request.getParameter("error") != null && request.getParameter("error") != "") { %>
						<div class="alert alert-danger" role="alert">
						  <%= request.getParameter("error") %>
						</div>
					<% } %>
					<h2 class="card-text fw-bold">Ciao ${utente.getNome()}${tecnico.getNome()}${amministratore.getNome()}</h2>
						
					<form action="ConnessioneDistributoreAutomaticoController" method="GET">
						<div class="form-floating mb-3">
							<input type="number" id="connessioneDA" class="form-control" name="idDistributore" placeholder="Connetti al distributore">
							<label for="connessioneDA" class="form-label">Connetti al distributore</label>
						</div>
						<button type="submit" class="w-50 btn btn-success text-center">Connetti</button>
					</form>
					<a class="btn btn-primary w-100 mb-1" href="CarteDiCreditoController">Gestione Carte di Credito</a>
					<a class="btn btn-primary w-100 mb-1" href="LogoutController">Logout</a>
				</div>
			</div>
		</div>
	</body>
</html>