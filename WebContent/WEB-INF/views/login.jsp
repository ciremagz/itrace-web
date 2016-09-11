<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin - Bootstrap Admin Template</title>


<spring:url value="/resources/css/bootstrap.min.css" var="bootStrapCss" />
<spring:url value="/resources/css/sb-admin.css" var="sbAdminCss" />
<spring:url value="/resources/css/plugins/morris.css" var="morrisCss" />
<spring:url value="/resources/font-awesome/css/font-awesome.min.css"
	var="customFont" />
<spring:url value="/resources/js/jquery.js" var="jqueryJs" />
<spring:url value="/resources/js/bootstrap.min.js" var="boostrapJs" />
<spring:url value="/resources/js/plugins/morris/raphael.min.js"
	var="raphaelJs" />
<spring:url value="/resources/js/plugins/morris/morris.min.js"
	var="morrisJs" />
<spring:url value="/resources/js/plugins/morris/morris-data.js"
	var="morrisDataJs" />


<!-- Bootstrap Core CSS -->
<link href="${bootStrapCss}" rel="stylesheet">
<!-- Custom CSS -->
<link href="${sbAdminCss }" rel="stylesheet">
<!-- Morris Charts CSS -->
<link href="${morrisCss}" rel="stylesheet">
<!-- Custom Fonts -->
<link href="${customFont} rel=" stylesheet" type="text/css">


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<p>you are in the login page</p>
	<p>${system_message}</p>
	<form action="login" method="post">
		<input type="text" name="username" placeholder="username"/>
		<input type="password" name="password" placeholder="********"/>
		<input type="submit" value="login">
	</form>

</body>

</html>
