<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-New User(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

	<form action="user?action=insertuser" method="post">
	
	<div>
	<h3>Enter Your Details Before Ordering</h3>
		<p> <label> Name</label><input name="patients" value=""> <br/>
		 	<label> Contact</label><input name="contact" value=""> <br/>
		 	<label> Email</label><input name="email" value=""> <br/>		   
		 </p>
	</div>
	
	<div>
		<p> 
		 	<input type="submit" name="Add User">    
		 </p>
	</div>
	
	
	
	</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>