<%@page import="ph.edu.usjr.team2.itrace.web.model.Song"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<spring:url value="/resources/javascript/main.js" var="mainJs"/>
	<script type="text/javascript" src="${mainJs}"></script>
</head>
<body>
	<form action="savePlaylist" method="POST">
		<input type="text" name="playlistName" placeholder="playlist name"/>
		<input type="submit" value="savePlaylist"/>
	</form>
	<a href="playlist"><button>back</button></a>
</body>
</html>