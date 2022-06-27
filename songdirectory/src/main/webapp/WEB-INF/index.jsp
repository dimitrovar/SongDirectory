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
<title>Dashboard</title>
    <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="container">
	<h1>Welcome, <c:out value="${user.userName}"/>!</h1>
	<a href="/logout" class="logout">logout</a>
	<h3>Song List</h3>
	<table class="table table-striped">
	    <thead>
	        <tr>
	            <th scope="col">Song</th>
	            <th scope="col"># of Times Edited</th>
	        </tr>
	    </thead>
	    <tbody>
			<c:forEach var="song" items="${songs}">
				<tr>
					<td><a href="/songs/${song.id}" class="name">${song.name}</a>
					<p>Artist: <c:out value="${song.artist}"/></p>
					<p>Genre: <c:out value="${song.genre}"/></p>
					</td>
					<td><c:out value="${song.updatedTimes}"/></td>
				</tr>	
			</c:forEach>
	    </tbody>
	</table>
	
	<form action="/songs/new" method="get">
	<input type="submit" value="Add a Song" class="btn btn-primary"/>
	</form>
	
	</body>
</html>