<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage = "true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>CoffeeBrew - Errore</title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%@include file="/includes/navbar.jsp"%>
		<div class="container-fluid row px-0 vh-100">
			<div class="card mx-auto my-auto" style="width: 28rem;">
				<div class="card-body" id="cardForm">
					<h5 class="card-title fw-bold">Si è verificato un errore</h5>
					<div class="card-text alert alert-danger" role="alert">
						<%=request.getAttribute("error")%>
					</div>
				</div>
				<div class="card-body border-top border-3">
					<a class="btn btn-primary w-100 mb-1" href="landing.jsp">Home</a>
				</div>
			</div>
		</div>
	</body>
</html>