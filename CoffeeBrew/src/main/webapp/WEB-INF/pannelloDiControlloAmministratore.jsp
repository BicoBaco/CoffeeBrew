<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="css/style.css">
		<title>CoffeeBrew - Pannello di Controllo Amministratore</title>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script src="js/formCheck.js"></script>
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
			<div class="container-fluid position-absolute vh-30">
			<div class="card mx-auto my-auto" style="width: 50rem;">
				<% if(request.getParameter("error") != null && request.getParameter("error") != "") { %>
					<%= request.getParameter("error") %>
				<% } %>
				</div>	
			</div>
			<div class="container-fluid row px-0 vh-100">		
				<div class="card mx-auto my-auto" style="width: 50rem;">
					<div class="card-header">
						<ul class="nav nav-tabs card-header-tabs">
						     <li class="nav-item">
						     	<a class="nav-link" href="#distributori" data-bs-toggle="tab">Distributori</a>
						     </li>
						     <li class="nav-item">
						     	<a class="nav-link" href="#tecnici" data-bs-toggle="tab">Tecnici</a>
						     </li>
						     <li class="nav-item">
						     	<a class="nav-link" href="#utenti" data-bs-toggle="tab">Utenti</a>
						     </li>
							<li class="nav-item ms-auto">
							     <a class="nav-link" href="LogoutController">Logout</a>
							</li>
    					</ul>    					
					</div>
					<div class="card-body">
						<div class="tab-content">
							<div class="tab-pane fade" id="distributori">
								<h5 class="card-title fw-bold">Gestione Distributori Automatici</h5><hr>	
								<table class="table">
									<tr>
										<th>Locazione</th>
									    <th>Utente connesso</th>
									    <th>Tecnico connesso</th>
									</tr>
								<% for(DistributoreAutomaticoBean distr : listaDistributori) { %>
									<tr>
										<td> <%= distr.getLocazione() %> </td>
										<td> <%= distr.getOccupanteUtente() %> </td>
										<td> <%= distr.getOccupanteTecnico() %> </td>
										<td> <form action="RimozioneDistributoreAutomaticoController" method="POST">
												<input type="hidden" id="idDistributore<%= distr.getIdDistributore() %>" name="idDistributore" value=<%= distr.getIdDistributore() %>>
												<input type="submit" value="Rimuovi"/> 
											</form> </td> 
									</tr>
								<% } %>
								</table> <br>
								<form action="RegistrazioneDistributoreAutomaticoController" method="POST">
									 Locazione <input type="text" name="locazione">
									 <input type="submit"/>	 
								</form> 
							</div>
							<div class="tab-pane fade" id="tecnici">
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
										<td> <%= tecnico.getNome() %> </td>
										<td> <%= tecnico.getCognome() %> </td>
										<td> <%= tecnico.getEmail() %> </td>
										<td> <form action="RimozioneTecnicoController" method="POST">
											<input type="hidden" id="idTecnico<%= tecnico.getIdTecnico() %>" name="idTecnico" value=<%= tecnico.getIdTecnico() %>>
												<input type="submit" value="Rimuovi"/> 
											</form> </td>
									</tr>
								<% } %>			
								</table> 					
								<h6 class="card-text fw-bold">Registrazione Tecnico</h6>
								<form action="RegistrazioneTecnicoController" method="POST">
									<div class="col">
										<div class="row">
											<label for="nome" class="form-label">Nome</label>
											<input type="text" class="form-control" name="nome">
										</div>
										<div class="row">
											<label for="cognome" class="form-label">Cognome</label>
											<input type="text" class="form-control" name="cognome">
										</div>
									</div>
									<div class="col">
										<div class="row">
											<label for="email" class="form-label">Indirizzo email</label>
											<input type="text" class="form-control" name="email">
										</div>
										<div class="row">
											<label for="password" class="form-label">Password</label>
											<input id="newPassword" type="password" class="form-control" name="password">
										</div>
									</div>
									<input type="submit"/>		 	 
								</form> 
							</div>
							<div class="tab-pane fade" id="utenti">
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
										<td> <%= utente.getNome() %> </td>
										<td> <%= utente.getCognome() %> </td>
										<td> <%= utente.getEmail() %> </td>
										<td> <form action="RimozioneUtenteController" method="POST">
											<input type="hidden" id="idUtente<%= utente.getIdUtente() %>" name="idUtente" value=<%= utente.getIdUtente() %>>
												<input type="submit" value="Rimuovi"/> 
											</form> </td>
									</tr>
									<% } %>			
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
		<script>
			$(document).ready(function(){
				var tab = window.location.hash
				if(tab === null || tab === "") tab = "#distributori";
				attivaTab(tab);
			});

			function attivaTab(tab){
				$('.nav-tabs a[href="' + tab + '"]').tab('show');
			};
		</script>
	</body>
</html>