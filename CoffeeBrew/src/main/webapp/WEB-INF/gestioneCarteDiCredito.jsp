<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>CoffeeBrew - Credito</title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%@ page import="java.util.ArrayList"%>
		<%@ page import="com.mvc.bean.CartaDiCreditoBean"%>
		<% 	ArrayList<CartaDiCreditoBean> listaCarte = 
				(ArrayList<CartaDiCreditoBean>) request.getAttribute("listaCarte");	%>
		<%@include file="/includes/navbar.jsp"%>	
		<div class="container-fluid row px-0 vh-100">		
			<div class="card mx-auto my-auto" style="width: 50rem;">
				<div class="card-body">
					<h5 class="card-title fw-bold">Gestione Carte di credito</h5><hr>
					<% if (request.getParameter("error") != null && request.getParameter("error") != "") { %>
						<div class="alert alert-danger" role="alert">
							<%=request.getParameter("error")%>
						</div>
					<% } %>
					<% if (request.getParameter("message") != null && request.getParameter("message") != "") { %>
						<div class="alert alert-success" role="alert">
							<%=request.getParameter("message")%>
						</div>
					<% } %>
					<table class="table">
						<tr>
							<th>Numero carta</th>
							<th>Nome sulla carta</th>
							<th>Data di scadenza</th>
						</tr>
						<% for (CartaDiCreditoBean cdc : listaCarte) { %>
						<tr>
							<td><%=cdc.getNumeroCarta()%></td>
							<td><%=cdc.getNomeSullaCarta()%></td>
							<td><%=cdc.getDataScadenza()%></td>
						</tr>
						<% } %>
				
					</table>

					<h6 class="card-title fw-bold">Inserisci Carta di credito</h6>	
					<form action="CarteDiCreditoController" method="POST">
						<div class="form-floating formfloating-sm mb-3">
							<input id="numeroCarta" type="text" class="form-control" name="numeroCarta" placeholder="Numero Carta">
							<label for="numeroCarta" class="form-label">Numero Carta</label> 
						</div>
						<div class="form-floating mb-3">
							<input id="nomeSullaCarta" type="text" class="form-control" name="nomeSullaCarta" placeholder="Nome sulla Carta">
							<label for="nomeSullaCarta" class="form-label">Nome sulla Carta</label> 
						</div>
						<div class="form-floating mb-3">
							<input id="dataScadenza" type="date" class="form-control" name="dataScadenza" placeholder="Data di scadenza">
							<label for="dataScadenza" class="form-label">Data di scadenza</label> 
						</div>
						<button type="submit" class="w-50 btn btn-success text-center">Inserisci</button>
					</form>
					
					<hr>
					<h6 class="card-title fw-bold">Credito attuale: ${utente.getCentesimiCredito()/100} &euro;</h6>
					<h6 class="card-title fw-bold">Ricarica il credito</h6>
					<form action="RicaricaCreditoController" method="POST">
						<div class="form-floating mb-3">
							<select class="form-select" name="idCarta" id="carte">
								<% for (CartaDiCreditoBean cdc : listaCarte) { %>
									<option value="<%= cdc.getIdCarta() %>"><%= cdc.getNumeroCarta() %></option>
								<% } %>
							</select>
							<label for="carte">Seleziona la carta</label>
						</div>
						<div class="form-floating mb-3">
							<input class="form-control" type="number" id="euroRicarica" name="euroRicarica">
							<label for="euroRicarica">Importo da ricaricare</label>
						</div>
						<button type="submit" class="w-50 btn btn-success text-center">Ricarica</button>
					</form>
					<a class="btn btn-primary w-100 mb-1" href="HomeUtenteController">Home</a>
				</div>
			</div>
		</div>
	</body>
</html>