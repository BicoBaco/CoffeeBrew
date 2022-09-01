<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>CoffeeBrew - Pannello di Controllo Tecnico</title>
	<style> @import url("css/style.css"); </style>
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
						<td> <form action="" method="POST">
								<input type="hidden" id="idDistributore<%= distr.getIdDistributore() %>" name="idDistributore" value=<%= distr.getIdDistributore() %>>
								<input type="submit" value="Idk, refilla"/> 
							</form> </td> 
					</tr>
					<% } %>			
				</table>

				<form action="ConnessioneDistributoreAutomaticoController" method="GET">
					<div class="mb-2">
						Connetti al distributore:
						<input type="number" name="idDistributore">
					</div>
					<button type="submit" class="btn btn-success text-center">Invia</button>	 
				</form> 
			</div>
			<div class="card-body border-top border-3">
					<a class="btn btn-primary w-100 mb-1" href="LogoutController">Logout</a>
			</div>
		</div>
	</div>
</body>
</html>


		
		
				
				