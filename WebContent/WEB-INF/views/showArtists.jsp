<%@page import="ph.edu.usjr.team2.itrace.web.model.Artist"%>
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
		List<Artist> artists = (List<Artist>) request.getAttribute("artists");
	%>
	<h1>Artist</h1>
	<p>show all list of Artist</p>	
	<table>
		<tr>
			<th></th>
			<th></th>
			<th>-</th>
			<th>Artist</th>
		</tr>
		<c:forEach var="i" items="${artists}" varStatus="loopCounter">
			<tr>
				<td>
					<form action="generatePlaylistByArtist" method="post">
						<input type="text" value="${i.getArtistName()}" name="artistName"
							hidden="true" /> <input type="submit" value="submit" />
					</form>
				</td>
				<td><c:out value="${loopCounter.count}.)" /></td>
				<td><c:out value="${i.getArtistName()}" /></td>
			</tr>
		</c:forEach>
	</table>
	<a href="library"><button>back</button></a>

</body>
</html>