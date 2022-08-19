<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<style>
			body {
			  background-image: url('https://i.ytimg.com/vi/kkBgqTXLgTw/maxresdefault.jpg');
			  background-repeat: no-repeat;
			  background-attachment: fixed;
			  background-size: 100% 100%;
			}
			div {
				text-align: center;
				background-color: white;
				margin-left: 40%;
				margin-top: 10%;
				margin-right: 40%;
			}
		</style>
		<title>Pannello di Controllo Amministratore</title>	
	</head>
	<body>
		<%@ page import="java.util.ArrayList" %>
		<%@ page import="com.mvc.bean.DistributoreAutomaticoBean" %>
		<%@ page import="com.mvc.bean.TecnicoBean" %>
		<% ArrayList<DistributoreAutomaticoBean> listaDistributori = 
			(ArrayList<DistributoreAutomaticoBean>) request.getAttribute("listaDistributori"); 
			ArrayList<TecnicoBean> listaTecnici = 
				(ArrayList<TecnicoBean>) request.getAttribute("listaTecnici");
		%>
		<div>
			<% if(request.getParameter("error") != null && request.getParameter("error") != "") { %>
				<%= request.getParameter("error") %>
			<% } %>
			
		</div>
		<div>
			<h2>Gestione Distributori Automatici</h2>			
			<table>
				<tr>
					<th>Locazione</th>
				    <th>Occupante</th>
				</tr>
			<% for(DistributoreAutomaticoBean distr : listaDistributori) { %>
				<tr>
					<th> <%= distr.getLocazione() %> </th>
					<th> <%= distr.getOccupante() %> </th>
					<th> <input type="hidden" id="idDistributore" name="idDistributore" value=<%= distr.getIdDistributore() %>>
						<button name="rimuoviDistributore" type="button">Rimuovi</button> </th>
				</tr>
			<% } %>			
			</table> 
		<br>
		
			<form action="RegistrazioneDistributoreAutomaticoController" method="POST">
				 Locazione <input type="text" name="locazione">
				 <input type="submit"/> <br>	 
			</form>
		</div>
		<div>
			<h2>Gestione Tecnici</h2>			
			<table>
				<tr>
					<th>Nome</th>
				    <th>Cognome</th>
				    <th>Email</th>
				</tr>
			<% for(TecnicoBean tecnico : listaTecnici) { %>
				<tr>
					<th> <%= tecnico.getNome() %> </th>
					<th> <%= tecnico.getCognome() %> </th>
					<th> <%= tecnico.getEmail() %> </th>
					<th> <button id="rimuoviTecnico" type="button">Rimuovi</button> </th>
				</tr>
			<% } %>			
			</table> 
		<br>
			Registrazione Tecnico
			<form action="RegistrazioneTecnicoController" method="POST">
				 Nome <input type="text" name="nome"> <br>
				 Cognome <input type="text" name="cognome"> <br>
				 Email <input type="email" name="email"> <br>
				 Password <input type="password" name="password"> <br>
				 <input type="submit"/> <br>	 
			</form>
		</div>
		<script>
			idDistributore = document.getElementsByName("idDistributore");
			rimuoviDistrButton = document.getElementsByName("rimuoviDistributore");
			
			for (let i = 0; i < rimuoviDistrButton.length; i++) {
			 	rimuoviDistrButton[i].addEventListener("click", function(){ rimuoviDistributore(i);	});
			}
			
			function rimuoviDistributore(i) {
				const xhttp = new XMLHttpRequest();
				xhttp.open("POST", "RimozioneDistributoreAutomaticoController", true);
				xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				xhttp.send("idDistributore=" + idDistributore[i].value);
				location.reload(true);
			}
			
			//TODO cancellare html delle cose rimosse dopo aver controllato l'esito
		</script>
	</body>
</html>