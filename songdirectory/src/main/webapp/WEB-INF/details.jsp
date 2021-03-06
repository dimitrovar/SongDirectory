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
<title>Song Details</title>
    <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="container">
    <h1 class="title"><c:out value="${song.name}"/></h1>
	<h2 class="username">Started by <c:out value="${song.user.userName}"/></h2>
	
	<h4>Artist: <c:out value="${song.artist}"/></h4>
	
	<h4>Genre: <c:out value="${song.genre}"/></h4>
	
	<h4>Lyrics:</h4> 
		
	<h5><c:out value="${song.lyrics}"/></h5>
   	
   	<a href="/songs/${song.id}/edit" class="btn btn-primary">Contribute</a>
   	
   	<form action="/songs/${song.id}" method="post">
    <input type="hidden" name="_method" value="delete">
    <input type="submit" value="delete" class="dbutton">
	</form>
	
</body>
</html>