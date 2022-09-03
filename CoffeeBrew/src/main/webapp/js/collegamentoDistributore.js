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