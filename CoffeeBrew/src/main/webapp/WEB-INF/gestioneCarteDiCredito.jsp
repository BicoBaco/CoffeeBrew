<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="com.mvc.bean.CartaDiCreditoBean"%>

	TODO Lista Carte
	<br>
	<%
	ArrayList<CartaDiCreditoBean> listaCarte = (ArrayList<CartaDiCreditoBean>) request.getAttribute("listaCarte");
	%>

	<table>
		<tr>
			<th>Numero carta</th>
			<th>Nome sulla carta</th>
			<th>Data di scadenza</th>
		</tr>
		<% for (CartaDiCreditoBean cdc : listaCarte) { %>
		<tr>
			<th><%=cdc.getNumeroCarta()%></th>
			<th><%=cdc.getNomeSullaCarta()%></th>
			<th><%=cdc.getDataScadenza()%></th>
		</tr>
		<% } %>

	</table>

	<br>
	<% if (request.getParameter("error") != null && request.getParameter("error") != "") { %>
		<%=request.getParameter("error")%>
	<% } %>
	<form action="CarteDiCreditoController" method="post">
		Numero Carta <input type="text" name="numeroCarta"> Nome sulla
		Carta <input type="text" name="nomeSullaCarta"> Data di
		scadenza <input type="date" name="dataScadenza"> <input
			type="submit" value="Inserisci">
	</form>
	
	<br>
	
	<form action="RicaricaCreditoController" method="post">
		Carta
		<select name="idCarta" id="cars">
			<% for (CartaDiCreditoBean cdc : listaCarte) { %>
				<option value="<%= cdc.getIdCarta() %>"><%= cdc.getNumeroCarta() %></option>
			<% } %>
		</select> <br>
		Importo da ricaricare <input type="number" name="euroRicarica">
		<input type="submit" value="ricarica">
	</form>

	<a href="landing.jsp">Home</a>
</body>
</html>