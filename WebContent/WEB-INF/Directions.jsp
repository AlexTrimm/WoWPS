<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WoWPS</title>
</head>
<body>
<h2>Welcome to WoWPS!</h2>
<br/>
<h3>Here are directions from ${startLoc} to ${endLoc}:</h3>
<br/>
<ol>
<c:forEach var='step' items="${steps}">
	<li>Go ${step.direction} to ${step.endLoc}.</li>
</c:forEach>
</ol>
<br/>
Click <a href="./Submit">here</a> to request new directions.
</body>
</html>