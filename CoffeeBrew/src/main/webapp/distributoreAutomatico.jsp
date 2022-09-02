<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Distributore Automatico</title>
		<link rel="icon" type="image/x-icon" href="images/favicon.ico">
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<div class="container-fluid row px-0 vh-100">
			<div class="card mx-auto my-auto w-50 bg-dark text-white bg-opacity-50 p-3" id="interfacciaUtente">
				<h3 class="display-2">Benvenuto <span id="nomeUtente"></span></h3>
				<p>Credito Residuo: &euro;<span id="credito"></span></p>
				<div class="w-100 text-center my-4">
					<h1>Selezionare un prodotto:</h1>
				</div>
				<div class="row text-center my-auto" id="pulsantiProdotti">
					<div class="col-md-4 themed-grid-col" id="templateSelezionaProdotto" hidden>
						<span id="nomeProdotto">Espresso</span><br>
						<span id="costoProdotto"></span>&euro;<br>
						<button class="prodotto btn" id="buttonProdotto">
							<img id="productImg" alt="" src="images/espresso.png" class="product-img">
						</button>
					</div>
				</div>
				<p id="scelta"></p>
				<input type="hidden" id="importo" name="importo" value="">
				<input type="hidden" id="utente" name="idUtente" value="">
				<input id="acquistaButton" type="submit" value="Acquista">
			</div>
		</div>
		
		<button id="poll" type="button">Polling</button>
		<input type="number" id="idDistributore" name="idDistributore">
		
		<div id="interfacciaTecnico" hidden>
			<h3>Benvenuto <span id="nomeTecnico"></span></h3>
			<div id="templateRicaricaProdotto"><span id="nomeRicaricaProdotto"></span>[<span id="qtyProdotto"></span>%]<button id="pulsanteRicarica">Ricarica</button></div>
			<div id="listaProdotti">
			
			<!--
				<span>Cappuccino:		<span id="qtyCappuccino">	</span><button onclick="ricaricaProdotto('cappuccino')">	Ricarica</button></span><br>
				<span>Espresso:			<span id="qtyEspresso">		</span><button onclick="ricaricaProdotto('espresso')">		Ricarica</button></span><br>
				<span>Cioccolata calda: <span id="qtyCioccolata">	</span><button onclick="ricaricaProdotto('cioccolata')">	Ricarica</button></span><br>
				<span>Tè verde:			<span id="qtyTe">			</span><button onclick="ricaricaProdotto('te verde')">		Ricarica</button></span><br>
			  -->
			</div>
			<input id="exitButton" onclick="exit()" type="submit" value="Esci">
		</div>
		
		<script src="polling.js"></script>
	</body>
</html>