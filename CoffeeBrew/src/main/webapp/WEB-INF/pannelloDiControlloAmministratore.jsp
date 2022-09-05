<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>CoffeeBrew - Pannello di Controllo Amministratore</title>
		<link rel="stylesheet" href="css/style.css">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		
	</head>
	<body>
		<%@ page import="java.util.ArrayList" %>
		<%@ page import="com.mvc.bean.DistributoreAutomaticoBean" %>
		<%@ page import="com.mvc.bean.TecnicoBean" %>
		<%@ page import="com.mvc.bean.UtenteBean" %>
		<%@ page import="com.mvc.bean.ProdottoBean" %>
		<% ArrayList<DistributoreAutomaticoBean> listaDistributori = 
			(ArrayList<DistributoreAutomaticoBean>) request.getAttribute("listaDistributori"); 
			ArrayList<TecnicoBean> listaTecnici = 
				(ArrayList<TecnicoBean>) request.getAttribute("listaTecnici");
			ArrayList<UtenteBean> listaUtenti = 
					(ArrayList<UtenteBean>) request.getAttribute("listaUtenti");
			ArrayList<ProdottoBean> listaProdotti = 
					(ArrayList<ProdottoBean>) request.getAttribute("listaProdotti");
		%>
		<%@include file="/includes/navbar.jsp"%>
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
					     <li class="nav-item">
					     	<a class="nav-link" href="#prodotti" data-bs-toggle="tab">Prodotti</a>
					     </li>
					     <li class="nav-item">
					     	<a class="nav-link" href="#statistiche" data-bs-toggle="tab">Statistiche</a>
					     </li>
						<li class="nav-item ms-auto">
						     <a class="nav-link" href="LogoutController">Logout</a>
						</li>
   					</ul>    					
				</div>
				<div class="card-body">
					<div class="tab-content">
						<% if(request.getParameter("error") != null && request.getParameter("error") != "") { %>
							<div class="alert alert-danger" role="alert">
				  				<%= request.getParameter("error") %>
							</div>
						<% } %>	
						<div class="tab-pane fade" id="distributori">
							<h5 class="card-title fw-bold">Gestione Distributori Automatici</h5><hr>	
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
									<td> <form action="RimozioneDistributoreAutomaticoController" method="POST">
											<input type="hidden" id="idDistributore<%= distr.getIdDistributore() %>" name="idDistributore" value=<%= distr.getIdDistributore() %>>
											<button type="submit" class="btn btn-outline-dark">Rimuovi</button>
										</form> </td> 
								</tr>
							<% } %>
							</table> <br>
							<h6 class="card-text fw-bold">Registrazione Distributore Automatico</h6>
							<form action="RegistrazioneDistributoreAutomaticoController" method="POST">
								<div class="form-floating mb-2">
									<input id="locazione" type="text" class="form-control" name="locazione" placeholder="Locazione" required>
									<label for="locazione" class="form-label">Locazione</label>
								</div>
								<button type="submit" class="btn btn-success text-center">Invia</button>	 
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
											<button type="submit" class="btn btn-outline-dark">Rimuovi</button>
										</form> </td>
								</tr>
							<% } %>			
							</table> 					
							<h6 class="card-text fw-bold">Registrazione Tecnico</h6>
							<form action="RegistrazioneTecnicoController" method="POST">
								<div class="form-floating mb-2">
									<input id="nome" type="text" class="form-control" name="nome" placeholder="Nome" required>
									<label for="nome" class="form-label">Nome</label>
								</div>
								<div class="form-floating mb-2">
									<input type="text" class="form-control" id="cognome" name="cognome" placeholder="Cognome" required>
									<label for="cognome" class="form-label">Cognome</label>
								</div>
								<div class="form-floating mb-2">
									<input type="text" class="form-control" id="email" name="email" placeholder="Indirizzo email" required>
									<label for="email" class="form-label">Indirizzo email</label>
								</div>
								<div class="input-group form-floating mb-2">
									<input id="password" type="password" class="form-control newPassword" name="password" aria-describedby="visualizzaBtn" placeholder="Password"
										pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*]).{8,}$" title="almeno un numero, una lettera maiuscola e minuscola, e almeno 8 caratteri" required>
									<button id="visualizzaBtn" type="button" class="btn btn-outline-secondary">Visualizza</button>
									<label for="password" class="form-label">Password</label>
								</div>									
								<button type="submit" class="btn btn-success text-center">Invia</button>		 	 
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
						<div class="tab-pane fade" id="prodotti">
							<h5 class="card-title fw-bold">Prodotti</h5><hr>			
							<table class="table">
								<tr>
									<th>idProdotto</th>
									<th>Nome</th>
								    <th>Costo</th>
								</tr>
							<% for(ProdottoBean prodotto : listaProdotti) { %>
								<tr>
									<td> <%= prodotto.getIdProdotto() %> </td>
									<td> <%= prodotto.getNome() %> </td>
									<td> <%= prodotto.getCosto() %> </td>
									<td> <form action="RimozioneProdottoController" method="POST">
											<input type="hidden" id="idProdotto<%= prodotto.getIdProdotto() %>" name="idProdotto" value=<%= prodotto.getIdProdotto() %>>
											<button type="submit" class="btn btn-outline-dark">Rimuovi</button>
										</form> </td> 
								</tr>
							<% } %>
							</table>
							<h6 class="card-text fw-bold">Inserimento Prodotto</h6>
							<form action="InserimentoProdottoController" method="POST">
								<div class="form-floating mb-2">
									<input id="nome" type="text" class="form-control" name="nome" placeholder="Nome" required>
									<label for="nome" class="form-label">Nome</label>
								</div>
								<div class="form-floating mb-2">
									<input type="number" class="form-control" id="costo" name="costo" placeholder="Costo" required>
									<label for="costo" class="form-label">Costo</label>
								</div>
								<div class="form-floating mb-2">
									<!--  -->
								</div>								
								<button type="submit" class="btn btn-success text-center">Invia</button>		 	 
							</form> 
						</div>
						<div class="tab-pane fade" id="statistiche">
							<h5 class="card-title fw-bold">Statistiche</h5><hr>			
							<div id="chart-container" >
								<canvas id="myChart"></canvas>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
		<% if (listaProdotti != null) { %>
		<script>
		  const data = {
				  labels: [
				  	<% for(int i = 0; i < listaProdotti.size()-1; i++) { %>
				    	'<%=listaProdotti.get(i).getNome()%>',
				   	<% } %>
				   	'<%=listaProdotti.get(listaProdotti.size()-1).getNome()%>'
				  ],
				  datasets: [{
				    label: 'My First Dataset',
				    data: [
				    	<% for(int i = 0; i < listaProdotti.size()-1; i++) { %>
					    	<%=listaProdotti.get(i).getQtaVendute()%>,
					    <% } %>
					    <%=listaProdotti.get(listaProdotti.size()-1).getQtaVendute()%>
				    ],
				    backgroundColor: [
				      'rgb(255, 99, 132)',
				      'rgb(54, 162, 235)',
				      'rgb(255, 205, 86)'
				    ],
				    hoverOffset: 4
				  }]
				};
		
		  const config = {
		    type: 'doughnut',
		    data: data,
		    options: {}
		  };
		  
		  const myChart = new Chart(
				    document.getElementById('myChart'),
				    config
				);
		</script>
		<% } %>
		<script src="js/formCheck.js"></script>
	</body>
</html>