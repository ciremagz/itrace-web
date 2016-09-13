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
		
		int counter = 0;
	%>
	<p>${system_message}</p>
	<p>songs in the database are as follows:</p>
	
	<table>
		<tr>
			<th></th>
			<th>-</th>
			<th>Title</th>
			<th>Artist</th>
		</tr>
		<c:forEach var="i" items="${songs}" varStatus="loopCounter">
			<tr>
				<td>
					<button onclick="addToPlaylist(this.id)" id="${i.getSongId()}">remove</button>
				</td>
				<td><c:out value="${loopCounter.count}.)" /></td>
				<td><c:out value="${i.getSongTitle()}" /></td>
				<td><c:out value="${i.getArtist().getArtistName()}" /></td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<form action="savePlaylist" method="POST">
		<input type="text" id="songIdList" name="songIdList" hidden="true"/>
		<input type="text" name="playlistName" placeholder="playlist name"/>
		<input type="submit" value="savePlaylist"/>
	</form>
	<%
		/* int counter = 0;
		for(Song e:songList){
			counter++;
			String songTitle = e.getSongTitle();
			String artistName = e.getArtist().getArtistName();
			out.print("<p>"+counter+")"+songTitle+","+artistName+"</p>");
		} */
	%>


	<a href="playlist"><button>back</button></a>
</body>
</html>