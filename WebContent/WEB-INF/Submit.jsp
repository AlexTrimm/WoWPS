<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WoWPS</title>
<script>
function validate(){
	if(document.userInput.startLoc.value == ""){
		alert("Please choose a start point!");
	}
	else if(document.userInput.endLoc.value == ""){
		alert("Please choose an end point!");
	}
	else if(document.userInput.startLoc.value == document.userInput.endLoc.value){
		alert('Please choose different start and end locations!');
	}
	else{
		document.userInput.submit();
	}

}
</script>
</head>
<body>
<h2>Welcome to WoWPS!</h2>
<br/>
<h3>Please choose a start point and an end point, then press submit!</h3>
<br/>
<br/>
<form name="userInput" id="userInput" action="Directions" method="post">
I want to go from 
<select name="startLoc" id="startLoc">
<option value="">Select a location</option>
<c:forEach var='location' items="${locations}">
	<option value="${location.ID}">${location.locationName}</option>
</c:forEach>
</select>
to 
<select name="endLoc" id="endLoc">
<option value="">Select a location</option>
<c:forEach var='location' items="${locations}">
	<option value="${location.ID}">${location.locationName}</option>
</c:forEach>
</select>

<button type="button" onclick="validate()">Submit</button>
</form>
</body>
</html>