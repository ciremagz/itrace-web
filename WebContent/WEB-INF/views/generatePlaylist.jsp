<%@page import="ph.edu.usjr.team2.itrace.web.model.Song"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		List<Song> songList = (List) request.getAttribute("songs");
		long[] songIds = new long[songList.size()];
		for(int i=0;i<songList.size();i++){
			songIds[i] = songList.get(i).getSongId();
		}
		int counter = 0;
	%>
	<p>${system_message}</p>
	<h2>${playlistName}</h2>
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
					<form action="removeToPlaylist" method="post">
						<input type="text"  name="songId" value="${i.getSongId()}" hidden="true"/>
						<input type="text" name="playlistId" value="${id}" hidden="true"/>
						<input type="submit" value="remove">
					</form>
				</td>
				<td><c:out value="${loopCounter.count}.)" /></td>
				<td><c:out value="${i.getSongTitle()}" /></td>
				<td><c:out value="${i.getArtist().getArtistName()}" /></td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<form action="saveGeneratedPlaylist" method="POST">
		<input type="text" name="songIds" value="${songIds}" hidden="true"/>
		<input type="text" name="playlistName" placeholder="playlist name">
		<input type="submit" value="save playlist"/>
	</form>
	<a href="library"><button>back</button></a>
</body>
</html>