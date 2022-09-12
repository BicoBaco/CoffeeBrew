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
		<%@ page import="java.util.ArrayList" %>
		<%@ page import="com.mvc.bean.ProdottoBean" %>
		<% 
			ArrayList<ProdottoBean> listaProdotti = 
					(ArrayList<ProdottoBean>) request.getAttribute("listaProdotti");
		%>
		<div class="container-fluid row px-0 vh-100 font-monospace">
			<div id="divPolling" class="mx-3 mt-3">
				<p class="text-white">Inserire codice del distributore</p>
				<button id="poll" type="button">Polling</button>
				<input type="number" id="idDistributore" name="idDistributore">
			</div>
			<div id="divCodiceDistributore" class="mx-3 mt-3" hidden>
				<p class="text-white">Codice del distributore: <span id="spanCodice"></span></p>
			</div>
			<div class="card w-75 mx-auto my-auto bg-dark text-white bg-opacity-50" id="divWait">
				<h3 class="display-4">In attesa di connessione<span id="textWait">...</span></h3>
			</div>
			<div class="card mx-auto my-auto w-50 bg-dark text-white bg-opacity-50 p-3" id="interfacciaUtente" hidden>
				<h3 class="display-3">Benvenuto <span id="nomeUtente"></span></h3>
				<p class="fw-bold ">Credito Residuo: &euro;<span id="credito"></span></p>
				<div class="w-100 text-center my-4">
					<h1>Selezionare un prodotto:</h1>
				</div>
				<div class="row text-center my-auto" id="pulsantiProdotti">
					<div class="col-md-4 themed-grid-col" id="templateSelezionaProdotto" hidden>
						<span id="nomeProdotto">Espresso</span><br>
						<span id="costoProdotto"></span>&euro;<br>
						<button class="prodotto btn" id="buttonProdotto">
							<img id="productImg" alt="" src="images/espresso.png" class="product-img" onerror="this.onerror=null;this.src='images/default.png';">
						</button>
					</div>
				</div>
				<p class="text-center fs-3" id="scelta"></p>
				<input type="hidden" id="importo" name="importo" value="">
				<input type="hidden" id="utente" name="idUtente" value="">
				<input class="btn btn-success" id="acquistaButton" type="submit" value="Acquista">
				<input class="btn btn-primary mt-3" id="exitButton" onclick="exit()" type="submit" value="Esci">
			</div>
			
			<div class="card mx-auto my-auto w-50 bg-dark text-white bg-opacity-50 p-3" id="interfacciaTecnico" hidden>
				<h3 class="display-2">Benvenuto <span id="nomeTecnico"></span></h3>
				<div class="w-100 text-center my-4">
					<h1>Selezionare prodotto da ricaricare:</h1>
				</div>
				<div class="row text-center my-auto" id="listaProdotti">
					<div class="col-md-4 themed-grid-col" id="templateRicaricaProdotto" hidden>
						<span id="nomeRicaricaProdotto">Espresso</span><br>
						<span id="qtyProdotto"></span>%<br>
						<img id="productImgRicarica" alt="" src="images/espresso.png" class="product-img" onerror="this.onerror=null;this.src='images/default.png';"><br>
						<button class="w-100 btn btn-success" id="pulsanteRicarica">Ricarica</button>
					</div>
				</div>
				<input class="btn btn-primary mt-3" id="exitButton" onclick="exit()" type="submit" value="Esci">
			</div>
		</div>
		<script src="js/prodotto.js"></script>
		<script type="text/javascript">
			let arrayProdotti = [];
			
			<% 
				if(listaProdotti != null) {
					for(ProdottoBean prodotto : listaProdotti) {
			%>
					<%= prodotto.getNome() %> = new Prodotto(<%= prodotto.getIdProdotto() %>, "<%= prodotto.getNome() %>", <%= prodotto.getCosto() %>, 50);
					arrayProdotti.push(<%= prodotto.getNome() %>);
			<% 
					}		
				} %>
		</script>
		<script src="js/polling.js"></script>
	</body>
</html>