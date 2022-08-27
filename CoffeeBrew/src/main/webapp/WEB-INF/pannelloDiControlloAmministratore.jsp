<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="css/style.css">
		<title>CoffeeBrew - Pannello di Controllo Amministratore</title>	
	</head>
	<body>
		<main>
			<%@ page import="java.util.ArrayList" %>
			<%@ page import="com.mvc.bean.DistributoreAutomaticoBean" %>
			<%@ page import="com.mvc.bean.TecnicoBean" %>
			<%@ page import="com.mvc.bean.UtenteBean" %>
			<% ArrayList<DistributoreAutomaticoBean> listaDistributori = 
				(ArrayList<DistributoreAutomaticoBean>) request.getAttribute("listaDistributori"); 
				ArrayList<TecnicoBean> listaTecnici = 
					(ArrayList<TecnicoBean>) request.getAttribute("listaTecnici");
				ArrayList<UtenteBean> listaUtenti = 
						(ArrayList<UtenteBean>) request.getAttribute("listaUtenti");
			%>
			<%@include file="/includes/navbar.jsp"%>
			<div class="container-fluid row">
				<div class="card mx-auto my-auto" style="width: 50rem;">
				<% if(request.getParameter("error") != null && request.getParameter("error") != "") { %>
					<%= request.getParameter("error") %>
				<% } %>
				</div>
			</div>
			<div class="container-fluid row">
				<div class="card mx-auto my-auto" style="width: 50rem;">
					<h5 class="card-title fw-bold">Gestione Distributori Automatici</h5><hr>		
					<table class="table">
						<tr>
							<th>Locazione</th>
						    <th>Utente connesso</th>
						    <th>Tecnico connesso</th>
						</tr>
					<% for(DistributoreAutomaticoBean distr : listaDistributori) { %>
						<tr>
							<th> <%= distr.getLocazione() %> </th>
							<th> <%= distr.getOccupanteUtente() %> </th>
							<th> <%= distr.getOccupanteTecnico() %> </th>
							<th> <form action="RimozioneDistributoreAutomaticoController" method="POST">
									<input type="hidden" id="idDistributore<%= distr.getIdDistributore() %>" name="idDistributore" value=<%= distr.getIdDistributore() %>>
									<input type="submit" value="Rimuovi"/> 
								</form> </th> 
						</tr>
					<% } %>
					</table> <br>
					<form action="RegistrazioneDistributoreAutomaticoController" method="POST">
						 Locazione <input type="text" name="locazione">
						 <input type="submit"/> <br>	 
					</form> <br>
				</div>
			</div>
			<div class="container-fluid row">
				<div class="card mx-auto my-auto" style="width: 50rem;">
					<h5 class="card-title fw-bold">Gestione Tecnici</h5><hr>		
					<table class="table">
						<tr>
							<th>Nome</th>
						    <th>Cognome</th>
						    <th>Email</th>
						    <th></th>
						</tr>
					<% for(TecnicoBean tecnico : listaTecnici) { %>
						<tr>
							<th> <%= tecnico.getNome() %> </th>
							<th> <%= tecnico.getCognome() %> </th>
							<th> <%= tecnico.getEmail() %> </th>
							<th> <form action="RimozioneTecnicoController" method="POST">
								<input type="hidden" id="idTecnico<%= tecnico.getIdTecnico() %>" name="idTecnico" value=<%= tecnico.getIdTecnico() %>>
									<input type="submit" value="Rimuovi"/> 
								</form> </th>
						</tr>
					<% } %>			
					</table> 
				<br>		
					<div class="card-body" id="cardForm">
						Registrazione Tecnico
						<form action="RegistrazioneTecnicoController" method="POST">
							<table>
								<tr>
									<th>Nome <input type="text" name="nome"></th>
									<th>Cognome <input type="text" name="cognome"></th>
								</tr>
								<tr>
									<th>Email <input type="email" name="email"></th>
									<th>Password <input type="password" id="newPassword" name="password"></th>
									<th><input type="submit"/></th>
								</tr>				
							</table>			 	 
						</form> 
					</div>
				</div> <br>
			</div>
			<div class="container-fluid row">
				<div class="card mx-auto my-auto" style="width: 50rem;">
					<h5 class="card-title fw-bold">Gestione Utenti</h5><hr>			
					<table class="table">
						<tr>
							<th>Nome</th>
						    <th>Cognome</th>
						    <th>Email</th>
						    <th></th>
						</tr>
						<% for(UtenteBean utente : listaUtenti) { %>
						<tr>
							<th> <%= utente.getNome() %> </th>
							<th> <%= utente.getCognome() %> </th>
							<th> <%= utente.getEmail() %> </th>
							<th> <form action="RimozioneUtenteController" method="POST">
								<input type="hidden" id="idUtente<%= utente.getIdUtente() %>" name="idUtente" value=<%= utente.getIdUtente() %>>
									<input type="submit" value="Rimuovi"/> 
								</form> </th>
						</tr>
						<% } %>			
					</table>
					<br>
					<a href="LogoutController">Logout</a>
				</div>
			</div>
		</main>
		<script src="js/formCheck.js"></script>
	</body>
</html>