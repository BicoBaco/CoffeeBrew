<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>CoffeeBrew - Home Utenti</title>
		<link rel="stylesheet" href="css/style.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
								
					<div id="formConnessione" class="form-floating mb-3">
						<input type="number" id="connessioneId" class="form-control" name="idDistributore" placeholder="Connetti al distributore">
						<label for="connessioneId" class="form-label">Connetti al distributore</label>
						<button id="connessioneButton" type="submit" class="w-50 btn btn-success text-center">Connetti</button>
					</div>
					
					<div class="alert hidden" role="alert" id="statoConnessione"></div>
						
					<a class="btn btn-primary w-100 mb-1" href="CarteDiCreditoController">Gestione Carte di Credito</a>
					<a class="btn btn-primary w-100 mb-1" href="LogoutController">Logout</a>
				</div>
			</div>
		</div>
	</body>
	<script>
		function feedback(risposta) {
			var stato = $("#statoConnessione");
			
			if(risposta == "Connesso") {
				$("#formConnessione").hide();	
				stato.removeClass('alert-danger alert-warning')
					.addClass('alert-success')
					.html("Connesso al distributore "+connessioneId.value)
					.show();
			} else if(risposta == "Occupato") {
				stato.removeClass('alert-success alert-danger')
					.addClass('alert-warning')
					.html("Il distributore "+connessioneId.value+" è occupato").show();
			} else {
				stato.removeClass('alert-warning alert-success')
					.addClass('alert-danger')
					.html(risposta).show();
			}
		}
	
		function avviaConnessione() {
			const xhttp = new XMLHttpRequest();
			xhttp.onload = function() {
				console.log("richiesta inviata al distr "+connessioneId.value);
				feedback(this.responseText);
			}
			xhttp.open("POST", "ConnessioneDistributoreAutomaticoController", true);
			xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhttp.send("idDistributore=" + connessioneId.value);
			console.log(connessioneId.value);
		}
			
		connessioneId = document.getElementById("connessioneId");
		connessioneButton = document.getElementById("connessioneButton");
		connessioneButton.addEventListener("click", avviaConnessione);
		
	</script>
</html>