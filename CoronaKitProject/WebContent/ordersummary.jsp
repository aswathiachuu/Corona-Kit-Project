<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Order Summary(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<div>
<h3>Order Summary</h3>
</div>

<div>User Details
<hr style="width:50%;text-align:left;margin-left:0">

<p> <label>${user } </label><p>
<p> <label>${contact } </label><p>
<p> <label>${eamil } </label><p>

</div>
<div>
<table border="1"   style="padding:10px" >
		<tr>
			
			<th>Name</th>
			<th>Price</th>
			<th>Quantity</th>
			<th> Amount </th>
			<th>Delete</th>			
			
		</tr>
		<c:forEach var="kitDetail" items="${kitDetails }">
			<tr>
				<td>
				<span>${kitDetail.coronaKitName }</span></td>
				<td>
				   <span>${kitDetail.amount }</span></td>
				<td>
				   <span>${kitDetail.quantity }</span></td>
				<td>
				<span>${kitDetail.amount * kitDetail.quantity}</span></td>
				<td>
				<span><a href="user?action=deleteitem&productId=${kitDetail.productId }">Delete</a></span></td>				
			</tr>
		</c:forEach>
		<tr colspan="3"  style="width:50%;text-align:right;margin-left:0" >
				<td>
				<span>${tamount }</span>
			</tr>
	</table>

</div>

<p> <a href="user?action=placeorder">Confirm Order</a> <a href="user?action=showproducts">Continue Shoping</a> </p>


<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>