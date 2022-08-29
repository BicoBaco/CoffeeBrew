<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>CoffeeBrew - Accesso Tecnici</title>
		<link rel="stylesheet" href="css/style.css">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script src="js/formCheck.js"></script>
	</head>
	<body>
		<%@include file="/includes/navbar.jsp"%>
		<div class="container-fluid row px-0 vh-100">
			<div class="card mx-auto my-auto" style="width: 28rem;">
				<div class="card-body" id="cardForm">
					<h5 class="card-title fw-bold">Accesso Tecnici</h5>
					<p class="card-text">
					<% if(request.getParameter("error") != null && request.getParameter("error") != "") { %>
						<%= request.getParameter("error") %>
					<% } %>
					<form action="AccessoTecnicoController" method="POST">
						<div class="form-floating mb-3">
							<input id="email" type="email" class="form-control" name="email" placeholder="Indirizzo email">
							<label for="email" class="form-label">Indirizzo email</label> 
						</div>
						<div class="input-group form-floating mb-3">
							<input type="password" class="form-control" id="password" name="password" placeholder="Password" aria-describedby="visualizzaBtn">
							<button id="visualizzaBtn" type="button" class="btn btn-outline-secondary">Visualizza</button>
							<label for="password" class="form-label">Password</label>
						</div>
						<div class="text-center w-100">
							<button type="submit" class="w-50 btn btn-success text-center">Accedi</button>
						</div>
					</form>
					</p>
				</div>
				<div class="card-body border-top border-3">
					<a class="btn btn-primary w-100 mb-1" href="landing.jsp">Home</a>
				</div>
			</div>
		</div>
	</body>
</html>