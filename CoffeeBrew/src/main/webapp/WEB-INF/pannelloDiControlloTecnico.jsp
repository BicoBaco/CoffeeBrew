<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Pannello di controllo Tecnico</title>
	<style> @import url("css/style.css"); </style>
</head>
<body>
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="com.mvc.bean.DistributoreAutomaticoBean" %>
	<div class="container bg-white">
		<% if(request.getParameter("error") != null && request.getParameter("error") != "") { %>
				<%= request.getParameter("error") %>
		<% } %>
	</div>
	<% ArrayList<DistributoreAutomaticoBean> listaDistributori = (ArrayList<DistributoreAutomaticoBean>) request.getAttribute("listaDistributori"); %>
	<div class="container bg-white">
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
				<th> <form action="" method="POST">
						<input type="hidden" id="idDistributore<%= distr.getIdDistributore() %>" name="idDistributore" value=<%= distr.getIdDistributore() %>>
						<input type="submit" value="Idk, refilla"/> 
					</form> </th> 
			</tr>
			<% } %>			
		</table>
		<br>
		
		<form action="ConnessioneDistributoreAutomaticoController" method="get">
			Connetti al distributore: <input type="number" name="idDistributore">
			<input type="submit">
		</form>
	</div>
</body>
</html>