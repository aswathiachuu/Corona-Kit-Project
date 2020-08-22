<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<div>


<p> <label>${user } </label>

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
				<span>${item.itemNo }</span></td>
				<td>
				<span>${item.itemName }</span></td>
				<td>
				<span>${item.itemDesc }</span></td>
				<td>
				<span>${item.itemPrice }</span></td>
				
			</tr>
		</c:forEach>
	</table>

</div>

<form action="user?action=saveorder" method="post">

<div>
		<p> <label>Item Number</label><input name="itemNumber" value="">
		 	<label>Quantity</label><input name="quantity">
		 	<input type="submit" name=" Add to Cart">    
		 </p>
	</div>

<div>	
<label> Number of Items in the Cart <strong>${noofcart }</strong>  and Total Bill Amount is <strong> ${tamount }</strong>  </label>
<hr />
 <h4> <a href="user?action=ordersummary">Click Here to View Order Summary </a></h4>  
 </div>
</form> 

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>