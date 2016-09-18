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
<spring:url value="/resources/css/main.css" var="mainCSS" />
<spring:url value="/resources/img/songpic.jpg" var="songPic"/>
<spring:url value="/resources/javascript/main.js" var="mainJs"/>
<script type="text/javascript" src="${mainJs}"></script>
<link href="${mainCSS}" rel="stylesheet">
</head>

<body>
	<%
		String songTitle = (String)request.getAttribute("songTitle");
		String artist = (String)request.getAttribute("artist");
		String playlistName = (String)request.getAttribute("playlistName");
		List<Song> songs = (List<Song>)request.getAttribute("songs");
	%>
	<script type="text/javascript">
		songQueue("${songs}");
	</script>
	<div class="image_holder">
		<img src="${songPic}">
	</div>
	<h1 id = "songTitle">${songTitle}</h1>
	<h2 id = "artist">${artist}</h2>
	<div id = "lyrics_holder">
		<button onclick="showLyrics(this.value)" id="showLyricsButton" value="false">show lyrics</button>
		<p id = "lyrics">lyrics</p>
	</div>
	
	<a></a>
	<hr>
	<h2>${playlistName }</h2>
	<p>next on queue placed here..</p>
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
	<a href="library"><button>back</button></a>
</body>

</html>