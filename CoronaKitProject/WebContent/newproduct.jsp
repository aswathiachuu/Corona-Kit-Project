<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Add New Product(Admin)</title>
</head>

<body>
<jsp:include page="header.jsp"/>
<hr/>

<h4>Add New Product</h4>

<form action="admin?action=insertproduct" method="post">
			
			<p> Item No <input type="text" value="" name="itemNo" /> </pItem>
			<p> Name <input type="text" value="" name="itemName" /> </pItem>
			<p> Description <input type="text" value="" name="itemDesc" /> </pItem>
			<p> Price <input type="text" value="" name="itemPrice" /> </pItem>
			
<div>
	 <p><input type="submit" name="Add"></p>	 
</div>
</form>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>