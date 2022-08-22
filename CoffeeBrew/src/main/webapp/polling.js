function isConnected() {
	if(!found) {
		const xhttp = new XMLHttpRequest();
		xhttp.onload = function() {
			utente = JSON.parse(this.responseText);
			if(utente.nome != null && utente.centesimiCredito != null && utente.idUtente != null) {
				if(utente.idUtente === "-1") {
					console.log("tecnico");
				} else {
					divInterfaccia.hidden = false;
					nomeLabel.innerHTML = utente.nome;
					creditoLabel.innerHTML = utente.centesimiCredito / 100;
					inputIdUtente.value = utente.idUtente;
					found = true;
					console.log(utente);
				}
			} else {
				console.log("nessun utente collegato");
			}
		}
		xhttp.open("GET", "DistributoreAutomaticoController?idDistributore=" + idTextbox.value, true);
		xhttp.send();
	}
}

function sendPurchase() {
	divInterfaccia.hidden = true;
	const xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		console.log("inviata POST");
		found = false;
	}
	xhttp.open("POST", "DistributoreAutomaticoController", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.send("importo=" + inputImporto.value + "&idDistributore=" + idTextbox.value + "&idUtente=" + inputIdUtente.value);
}

let found = false;

document.getElementById("poll").addEventListener("click", startPolling, false);
idTextbox = document.getElementById("idDistributore");
divInterfaccia = document.getElementById("interfaccia");
acquistaButton = document.getElementById("acquistaButton");
acquistaButton.addEventListener("click", sendPurchase, false);
nomeLabel = document.getElementById("nome");
creditoLabel = document.getElementById("credito");
pulsantiProdotto = document.getElementsByName("prodotto");
sceltaLabel = document.getElementById("scelta");
inputImporto = document.getElementById("importo");
inputIdUtente = document.getElementById("utente");

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
