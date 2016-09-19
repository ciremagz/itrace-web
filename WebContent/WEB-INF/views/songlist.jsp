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
</head>
<body>
	<%
		List<Song> songs = (List<Song>) request.getAttribute("songs");
		int counter = 0;
	%>

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
					<form action="showAddToPlaylist" method="post">
						<input type="text" value="${i.getSongId()}" name="songId" hidden="true"/>
						<input type="submit" value="add to playlist"/>
					</form>
				</td>
				<td><c:out value="${loopCounter.count}.)" /></td>
				<td><c:out value="${i.getSongTitle()}" /></td>
				<td><c:out value="${i.getArtist().getArtistName()}" /></td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<a href="library"><button>back</button></a>
</body>
</html>