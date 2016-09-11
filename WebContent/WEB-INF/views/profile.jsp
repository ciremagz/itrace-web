<%@page import="ph.edu.usjr.team2.itrace.web.model.User"%>
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
		User user = (User)request.getAttribute("user");
	%>
	<h1>Profile</h1>
	<p>username: ${user.getUsername()}</p>
	<a href="library"><button>back</button></a>
</body>
</html>