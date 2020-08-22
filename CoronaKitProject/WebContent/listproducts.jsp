<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<h4>Product List</h4>

<table border="1"   style="padding:2px" >
		<tr>
			<th>Item No</th>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
			
		</tr>
		<c:forEach var="item" items="${itemList }">
			<tr>
				<td>
				<span>${item.itemNo } </span></td>
				<td>
				<span>${item.itemName } </span></td>
				<td>
				<span>${item.itemDesc }</span></td>
				<td>
				<span>${item.itemPrice }</span></td>
				
			</tr>
		</c:forEach>
	</table>
	<nav>
<p><a href="admin?action=newproduct">Add Product</a> <span> | </span>
<a href="admin?action=editproduct&operaction=editproduct">Modify Product</a> <span> | </span>
<a href="admin?action=editproduct&operaction=deleteproduct">Delete Product</a> <span> | </span>
</p>
</nav><hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>