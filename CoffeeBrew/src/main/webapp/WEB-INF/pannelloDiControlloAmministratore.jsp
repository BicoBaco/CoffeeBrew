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
					<th> <form action="RimozioneDistributoreAutomaticoController" method="POST">
							<input type="hidden" id="idDistributore<%= distr.getIdDistributore() %>" name="idDistributore" value=<%= distr.getIdDistributore() %>>
							<input type="submit" value="Rimuovi"/> 
						</form> </th> 
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
					<th> <form action="RimozioneTecnicoController?idTecnico=<%= tecnico.getIdTecnico() %>" method="POST">
							<input type="submit" value="Rimuovi"/> 
						</form> </th>
				</tr>
			<% } %>			
			</table> 
		<br>
			Registrazione Tecnico
			<form action="RegistrazioneTecnicoController" method="POST">
				 Nome <input type="text" name="nome"> <br>
				 Cognome <input type="text" name="cognome"> <br>
				 Email <input type="email" name="email"> <br>
				 Password <input type="password" id="newPassword" name="password"> <br>
				 <input type="submit"/> <br>	 
			</form>
		</div>
		<script src="js/formCheck.js"></script>
	</body>
</html>