<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Song Database</title>
    <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="container">

	<h1 class = "song">Song Database</h1>

	<h2>Register</h2>

	<div class="container">
		<form:form action="/register" modelAttribute="newUser" class="form">
		
			<div>
			 	<form:errors path="userName" class="error"/>
				<form:label for="userName" path="userName">User Name:</form:label>
				<form:input type="text" path="userName" class="form-control"/>
			</div>
			
			<div>
				<form:errors path="email" class="error"/>
				<form:label for="email" path="email">Email:</form:label>
				<form:input type="text" path="email" class="form-control"/>
			</div>
			
			<div>
				<form:errors path="password" class="error"/>
				<form:label for="password" path="password">Password:</form:label>
				<form:input type="password" path="password" class="form-control"/>
			</div>
			
			<div>
				<form:errors path="confirm" class="error"/>
				<form:label for="" path="confirm">Confirm Password:</form:label>
				<form:input type="password" path="confirm" class="form-control"/>
			</div>
			
			<div>
				<input type="submit" value="Submit" class="btn btn-primary"/>
			</div>
			
		</form:form>
	</div>
	
	<h2 class="login">Login</h2>

	<div class="container">
		<form:form action="/login" modelAttribute="newLogin" class="form">
			<div>
				<form:errors path="email" class="error"/>
				<form:label for="email" path="email">Email:</form:label>
				<form:input type="text" path="email" class="form-control"/>
			</div>
			
			<div>
				<form:errors path="password" class="error"/>
				<form:label for="password" path="password">Password:</form:label>
				<form:input type="password" path="password" class="form-control"/>
			</div>
			
			<div>
				<input type="submit" value="Submit" class="btn btn-primary"/>
			</div>
		
		</form:form>
	</div>

</div>

</body>
</html>