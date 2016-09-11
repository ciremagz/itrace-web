<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Syntones</title>
</head>
<body>
	<p>you are in the login page</p>
	<p>${system_message}</p>
	<form action="login" method="post">
		<input type="text" name="username" placeholder="username"/>
		<input type="password" name="password" placeholder="********"/>
		<input type="submit" value="login">
	</form>
	<a href="registration"><input type="button" name="Register" value = "register"></a>
</body>
</html>