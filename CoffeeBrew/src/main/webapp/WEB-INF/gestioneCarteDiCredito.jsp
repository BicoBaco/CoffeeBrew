<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>CoffeeBrew - Credito</title>
		<link rel="stylesheet" href="css/style.css">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	</head>
	<body>
		<%@ page import="java.util.ArrayList"%>
		<%@ page import="com.mvc.bean.CartaDiCreditoBean"%>
		<% 	ArrayList<CartaDiCreditoBean> listaCarte = 
				(ArrayList<CartaDiCreditoBean>) request.getAttribute("listaCarte");	%>
		<%@include file="/includes/navbar.jsp"%>	
		<div class="container-fluid row px-0 vh-100">		
			<div class="card mx-auto my-auto" style="width: 50rem;">
				<div class="card-header">
					<ul class="nav nav-tabs card-header-tabs">
					     <li class="nav-item">
					     	<a class="nav-link" href="#carte" data-bs-toggle="tab">Carte di Credito</a>
					     </li>
					     <li class="nav-item">
					     	<a class="nav-link" href="#ricarica" data-bs-toggle="tab">Ricarica</a>
					     </li>
						<li class="nav-item ms-auto">
						     <a class="nav-link" href="HomeUtenteController">Home</a>
						</li>
   					</ul>    					
				</div>
				<div class="card-body">
					<div class="tab-content">
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
						<div class="tab-pane fade" id="carte">
							<h5 class="card-title fw-bold">Gestione Carte di credito</h5><hr>
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
									<td> <form action="RimozioneCartaDiCreditoController" method="POST">
											<input type="hidden" id="idCarta<%= cdc.getIdCarta() %>" name="idCarta" value=<%= cdc.getIdCarta() %>>
											<button type="submit" class="btn btn-outline-dark">Rimuovi</button>
										</form> </td> 
								</tr>
								<% } %>
						
							</table>
		
							<h6 class="card-title fw-bold">Inserisci Carta di credito</h6>	
							<form action="InserimentoCartaDiCreditoController" method="POST">
								<div class="form-floating formfloating-sm mb-3">
									<input id="numeroCarta" type="text" class="form-control" name="numeroCarta" placeholder="Numero Carta" maxlength=19 pattern="[0-9]{4}(-[0-9]{4}){3}">
									<label for="numeroCarta" class="form-label">Numero Carta</label> 
								</div>
								<div class="form-floating mb-3">
									<input id="nomeSullaCarta" type="text" class="form-control" name="nomeSullaCarta" placeholder="Nome sulla Carta" required>
									<label for="nomeSullaCarta" class="form-label">Nome sulla Carta</label> 
								</div>
								<div class="form-floating mb-3">
									<input id="dataScadenza" type="date" class="form-control" name="dataScadenza" placeholder="Data di scadenza" required>
									<label for="dataScadenza" class="form-label">Data di scadenza</label> 
								</div>
								<button type="submit" class="w-50 btn btn-success text-center">Inserisci</button>
							</form>
						</div>
						<div class="tab-pane fade" id="ricarica">
							<h5 class="card-title fw-bold">Credito attuale: <%= ((int) request.getAttribute("centesimiCredito"))/100.0 %> &euro;</h5>
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
						</div>
					</div>
				</div>
			</div>
		</div>
		<script>
			$(document).ready(function(){
				var tab = window.location.hash
				if(tab === null || tab === "") tab = "#carte";
				attivaTab(tab);
			});

			function attivaTab(tab){
				$('.nav-tabs a[href="' + tab + '"]').tab('show');
			};
			
			$('#numeroCarta').keyup(function() {
				  var numero = $(this).val().split("-").join("");	//split delle cifre tra i - in elementi di un array per poi unirli

				  if (numero.length > 0) {
					  numero = numero.match(new RegExp('.{1,4}', 'g')).join("-");
				  }
				  $(this).val(numero);
				});
			
			dataScadenza.min = new Date().toISOString().split("T")[0];
		</script>
	</body>
</html>