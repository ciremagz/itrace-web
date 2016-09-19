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

	<%
		List<Song> songList = (List) request.getAttribute("songs");
		String id = String.valueOf((long)request.getAttribute("id")) ;
		String playlistName = (String) request.getAttribute("playlistName");
		int counter = 0;
	%>
	<p>${system_message}</p>
	<h2>${playlistName}</h2>
	<table>
		<tr>
			<th></th>
			<th></th>
			<th>-</th>
			<th>Title</th>
			<th>Artist</th>
		</tr>
		<c:forEach var="i" items="${songs}" varStatus="loopCounter">
			<tr>
				<td>
					<form action="removeToPlaylist" method="post">
						<input type="text"  name="songId" value="${i.getSongId()}" hidden="true"/>
						<input type="text" name="playlistId" value="${id}" hidden="true"/>
						<input type="submit" value="remove">
					</form>
					
				</td>
				<td>
					<form action="playThisPlaylist" method="post">
						<input type="text"  name="songId" value="${i.getSongId()}" hidden="true"/>
						<input type="text" name="playlistId" value="${id}" hidden="true"/>
						<input type="submit" value="play">
					</form>
				</td>
				<td><c:out value="${loopCounter.count}.)" /></td>
				<td><c:out value="${i.getSongTitle()}" /></td>
				<td><c:out value="${i.getArtist().getArtistName()}" /></td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<form action="playPlaylist" method="POST">
		<input type="text" hidden="true" name="id" value="${id}">
		<input type="submit" value="play all"/>
	</form>
	<a href="library"><button>back</button></a>
</body>
</html>