<%@page import="ph.edu.usjr.team2.itrace.web.model.Playlist"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		List<Playlist> playlists = (List<Playlist>)request.getAttribute("playlists");
	%>
	<h1>Playlist</h1>
	<p>show all list of playlist created by the user here</p>
	<a href="newPlaylist"><button>Make new Playlist</button></a>
	<p>${system_message}</p>
	<table>
		<tr>
			<th></th>
			<th>-</th>
			<th>Playlist Name</th>
		</tr>
		<c:forEach var="i" items="${playlists}" varStatus="loopCounter">
			<tr>
				<td>
					<button onclick="" id="${i.getPlaylistId()}">edit</button>
				</td>
				<td><c:out value="${loopCounter.count}.)" /></td>
				<td><c:out value="${i.getPlaylistName()}" /></td>
			</tr>
		</c:forEach>
	</table>
	<a href="library"><button>back</button></a>
</body>
</html>