<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Place Order(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<h3>Order Confirmation</h3>

<div>
<p> Dear   ${user }    Order Id   ${orderid } has bean processed. 
Summary send to Contact number ${contact}.
Invoice has bean send to Email ${email }
</p>
</div>

<div>
<p>Thank you for shoping</p>
</div>    

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>