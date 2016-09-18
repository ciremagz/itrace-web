<%@page import="ph.edu.usjr.team2.itrace.web.model.Playlist"%>
<%@page import="java.util.List"%>
<%@page import="ph.edu.usjr.team2.itrace.web.model.UserTransaction"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		String username = (String) request.getSession().getAttribute("username");
		List<Playlist> playlists = (List<Playlist>)request.getAttribute("playlists");
	%>
	<h1>Your Library</h1>
	<br>
	<p>${system_message }</p>
	<br>
	<p>Welcome ${username}</p>

	<a href="profile"><button>show profile</button></a>
	<br>
	<a href="songList"><button>songs saved but not on a
			playlist</button></a>
	<br>
	<a href="playlist"><button>show playlist</button></a>

	<p>recently played playlist</p>

	<table>
		<tr>
			<th></th>
			<th>-</th>
			<th>Playlist Name</th>
		</tr>
		<c:forEach var="i" items="${playlists}" varStatus="loopCounter">
			<tr>
				<td>
					<form action ="playlistSongs" method="post">
						<input type="text" hidden="" name="id" value="${i.getPlaylistId()}">
						<input type="submit" value="show">
					</form>
				</td>
				<td><c:out value="${loopCounter.count}.)" /></td>
				<td><c:out value="${i.getPlaylistName()}" /></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>