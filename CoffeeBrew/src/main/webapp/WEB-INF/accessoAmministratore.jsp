<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Accesso Amministratore</title>
	</head>
	<body>
		<% if(request.getParameter("error") != null && request.getParameter("error") != "") { %>
				<%= request.getParameter("error") %>
		<% } %>
		<form action="AccessoAmministratoreController" method="POST">
			 Email <input type="email" name="email"> <br>
			 Password <input type="password" name="password"> 
			  <input type="button" id="passButton" value="Visualizza"><br>
			 <input type="submit"/> <br> 
		</form>
		<a href="index.jsp">Home</a>
	</body>
	<script src="js/formCheck.js"></script>
</html>