function isConnected() {
	if(!found) {
		const xhttp = new XMLHttpRequest();
		xhttp.onload = function() {
			occupante = JSON.parse(this.responseText);
			
			if(occupante.isTecnico != null) {
				if(occupante.isTecnico) {
					if(occupante.nome != null && occupante.idTecnico!= null) {
						found = true;
						
						divInterfacciaTecnico.hidden = false;
						nomeTecnicoLabel.innerHTML = occupante.nome;
						
						console.log(occupante);
					} else {
						console.log("errore dati tecnico")
					}
				} else {
					if(occupante.nome != null && occupante.centesimiCredito != null && occupante.idUtente != null) {
						found = true;
						
						divInterfacciaUtente.hidden = false;
						nomeUtenteLabel.innerHTML = occupante.nome;
						creditoLabel.innerHTML = occupante.centesimiCredito / 100;
						inputIdUtente.value = occupante.idUtente;
						console.log(occupante);
					} else {
						console.log("errore dati utente")
					}
				}
			} else {
				console.log("nessun utente collegato");
			}
		}
		xhttp.open("GET", "DistributoreAutomaticoController?idDistributore=" + idTextbox.value, true);
		xhttp.send();
	}
}

function sendPurchase(prodotto) {
	divInterfacciaUtente.hidden = true;
	const xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		console.log("inviata POST");
		found = false;
	}
	xhttp.open("POST", "DistributoreAutomaticoController", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.send("importo=" + inputImporto.value + "&idDistributore=" + idTextbox.value + "&idUtente=" + inputIdUtente.value);
}

function exit() {
	divInterfacciaUtente.hidden = true;
	divInterfacciaTecnico.hidden = true;
	const xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		console.log("inviata POST");
		found = false;
	}
	xhttp.open("POST", "DistributoreAutomaticoController", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.send("exit=true&idDistributore=" + idTextbox.value);
}

quantitaProdotti = {"cappuccino": 80, "espresso": 100, "cioccolata": 50, "te verde": 95};

function ricaricaProdotto(prodotto) {
	quantitaProdotti[prodotto] = 100;
	document.getElementById("qty" + prodotto.charAt(0).toUpperCase() + prodotto.slice(1)).innerHTML = 100;
}

let found = false;

document.getElementById("poll").addEventListener("click", startPolling, false);
idTextbox = document.getElementById("idDistributore");
divInterfacciaUtente = document.getElementById("interfacciaUtente");
divInterfacciaTecnico = document.getElementById("interfacciaTecnico");
acquistaButton = document.getElementById("acquistaButton");
acquistaButton.addEventListener("click", sendPurchase, false);
nomeUtenteLabel = document.getElementById("nomeUtente");
nomeTecnicoLabel = document.getElementById("nomeTecnico");
creditoLabel = document.getElementById("credito");
pulsantiProdotto = document.getElementsByName("prodotto");
sceltaLabel = document.getElementById("scelta");
inputImporto = document.getElementById("importo");
inputIdUtente = document.getElementById("utente");
divListaProdotti = document.getElementById("listaProdotti");
/*
qtyCappuccino = document.getElementById("qtyCappuccino");
qtyEspresso = document.getElementById("qtyEspresso");
qtyCioccolata = document.getElementById("qtyCioccolata");
qtyTeVerde = document.getElementById("qtyTe");
*/

/*
for(var nomeProdotto in quantitaProdotti) {
	nameLabel = document.createElement("span");
	nameLabel.innerHTML = nomeProdotto.charAt(0).toUpperCase() + nomeProdotto.slice(1) + ": " + quantitaProdotti[nomeProdotto];
	divListaProdotti.appendChild(nameLabel);
	
	refillButton = document.createElement("button");
	refillButton.addEventListener("click", function() {ricaricaProdotto(nomeProdotto)}, false);
	refillButton.innerHTML = "Ricarica";
	divListaProdotti.appendChild(refillButton);
	
	breakrow = document.createElement("br");
	divListaProdotti.appendChild(breakrow);
}
*/

function startPolling() {
	console.log("starting polling...")
	const interval = setInterval(isConnected, 2000);
}

for (let i = 0; i < pulsantiProdotto.length; i++) {
 	pulsantiProdotto[i].addEventListener("click", setCost, false);
	console.log("aggiunto");
}

function setCost() {
	inputImporto.value = this.value;
	console.log("premuto " + this);
}
