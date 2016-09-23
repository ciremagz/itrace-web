<%@page import="ph.edu.usjr.team2.itrace.web.model.Tag"%>
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
	<spring:url value="/resources/javascript/main.js" var="mainJs" />
	<script type="text/javascript" src="${mainJs}"></script>
</head>
<body>
	<%
		List<Tag> tags = (List<Tag>) request.getAttribute("tags");
	%>

	<table>
		<tr>
			<th></th>
			<th>-</th>
			<th>Tags</th>
		</tr>
		<c:forEach var="i" items="${tags}" varStatus="loopCounter">
			<tr>
				<td>
					<button onclick="pushTag(this.id)" id="${i.getTag()}">push</button>
				</td>
				<td><c:out value="${loopCounter.count}.)" /></td>
				<td><c:out value="${i.getTag()}" /></td>
			</tr>
		</c:forEach>
	</table>
	<form action="generatePlaylistByTag" method="post">
		<input type="text" id="tags" name="tags" hidden="true" /> <input
			type="submit" value="submit" />
	</form>
	<a href = "library"><button>cancel</button></a>

</body>
</html>