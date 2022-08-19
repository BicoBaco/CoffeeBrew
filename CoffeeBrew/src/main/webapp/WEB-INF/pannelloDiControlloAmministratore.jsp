<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Pannello di Controllo Amministratore</title>
	</head>
	<body>
		<div style="center;">
			Registrazione Tecnico
			<form action="RegistrazioneTecnicoController" method="POST">
				 Nome <input type="text" name="nome"> <br>
				 Cognome <input type="text" name="cognome"> <br>
				 Email <input type="email" name="email"> <br>
				 Password <input type="password" name="password"> <br>
				 <input type="submit"/> <br>	 
			</form>
			Inserisci Distributore Automatico
			<form action="RegistrazioneDistributoreAutomaticoController" method="POST">
				 Locazione <input type="text" name="locazione"> <br>
				 <input type="submit"/> <br>	 
			</form>
		</div>
	</body>
</html>