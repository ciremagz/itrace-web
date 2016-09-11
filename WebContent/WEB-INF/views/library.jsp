<%@page import="ph.edu.usjr.team2.itrace.web.model.UserTransaction"%>
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
		String username = (String)request.getSession().getAttribute("username");
	%>
	<h1>Your Library</h1><br>
	<p>${system_message }</p><br>
	<p>Welcome ${username}</p>

	<a href="profile"><button>show profile</button></a><br>
	<a href="songList"><button>songs saved but not on a playlist</button></a><br>
	<a href="playlist"><button>show playlist</button></a>
	<p> list recent playlist being played here...</p>
	

</body>
</html>