<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>CoffeeBrew - Pannello di Controllo Tecnico</title>
		<link rel="stylesheet" href="css/style.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	</head>
	<body>
		<%@ page import="java.util.ArrayList" %>
		<%@ page import="com.mvc.bean.DistributoreAutomaticoBean" %>
		<%@ include file="/includes/navbar.jsp" %>
		<% ArrayList<DistributoreAutomaticoBean> listaDistributori = 
			(ArrayList<DistributoreAutomaticoBean>) request.getAttribute("listaDistributori"); %>
			
		<div class="container-fluid row px-0 vh-100">		
			<div class="card mx-auto my-auto" style="width: 50rem;">
				<div class="card-body">
					<h5 class="card-title fw-bold">Gestione Distributori Automatici</h5><hr>
					<p class="card-text">
						<% if(request.getParameter("error") != null && request.getParameter("error") != "") { %>
							<div class="alert alert-danger" role="alert">
							  <%= request.getParameter("error") %>
							</div>
						<% } %>
					</p>		
					<table class="table">
						<tr>
							<th>Locazione</th>
							<th>Codice</th>
						    <th>Utente connesso</th>
						    <th>Tecnico connesso</th>
						</tr>
						<% for(DistributoreAutomaticoBean distr : listaDistributori) { %>
						<tr>
							<td> <%= distr.getLocazione() %> </td>
							<td> <%= distr.getIdDistributore() %> </td>
							<td> <%= distr.getOccupanteUtente() %> </td>
							<td> <%= distr.getOccupanteTecnico() %> </td>
						</tr>
						<% } %>			
					</table>
					
					<div id="formConnessione" class="form-floating mb-3">
						<div class="input-group form-floating mb-2">
							<input type="number" id="connessioneId" class="form-control" name="idDistributore" placeholder="Connetti al distributore">
							<label for="connessioneId" class="form-label">Connetti al distributore</label>
							<button id="connessioneButton" type="submit" class="w-25 btn btn-success text-center">Connetti</button>
						</div>
					</div>
					
					<div class="alert hidden" role="alert" id="statoConnessione"></div>
				</div>
				<div class="card-body border-top border-3">
					<a class="btn btn-primary w-100 mb-1" href="LogoutController">Logout</a>
				</div>
			</div>
		</div>
		<script src="js/collegamentoDistributore.js"></script>
	</body>
</html>	