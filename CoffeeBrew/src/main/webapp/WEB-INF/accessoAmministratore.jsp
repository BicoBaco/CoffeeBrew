<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Accesso Amministratore</title>
	</head>
	<body>
		<form action="AccessoAmministratoreController" method="POST">
			 Email <input type="email" name="email"> <br>
			 Password <input type="password" name="password"> <br>
			 <input type="submit"/> <br> 
		</form>
		<a href="index.jsp">Home</a>
	</body>
</html>