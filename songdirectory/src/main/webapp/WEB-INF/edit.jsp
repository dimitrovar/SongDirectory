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
<title>Edit Song</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"/>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/style.css">
</head>
<body class="inputstyle">
<h1 class="edit">Edit Song</h1>

<a href="/songs/" class="back">Go back</a>
	<form:form action="/songs/${song.id}/edit" method="post" modelAttribute="song">
		<input type="hidden" name="_method" value="put">
		
		<p>
			<form:label path="name" class="textbox">Song Name: </form:label>
			<form:errors path="name" class="error"/>
			<form:input path="name"/>
		</p>
		
		<p>
			<form:label path="artist" class="textbox">Artist Name: </form:label>
			<form:errors path="artist" class="error"/>
			<form:input path="artist"/>
		</p>
	
		<p>
			<form:label path="genre" class="textbox">Genre: </form:label>
			<form:errors path="genre" class="error"/>
			<form:input path="genre"/>
		</p>
		
		<p>
			<form:label path="lyrics" class="lyricsbox">Lyrics: </form:label>
			<form:errors path="lyrics" class="error"/>
			<form:textarea rows="3" path="lyrics"/>
		</p>
		
			<input type="submit" value="Submit" class="btn btn-primary"/>
			<a href="/songs" class="btn btn-primary">Cancel</a>

	
	</form:form>
</body>
</html>