<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p> you are in the search page </p>
	<form action="search" method="post">
		<input type="text" name="searchString" /> 
		<input type="submit" value="search" />
	</form>
	
	<p>${system_message}</p>
	<p>	search result are as follows:</p>
	<a href="home"><button>back</button></a>
</body>
</html>