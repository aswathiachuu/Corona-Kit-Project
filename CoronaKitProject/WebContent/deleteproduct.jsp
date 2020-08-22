<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Edit Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<form action="admin?action=deleteproduct" method="post">
<p> <label>Select Item number</label>
	<select name="itemNo" >
  		<option value="-1">Select</option>
  	 <c:forEach var="itemCodes" items="${itemCodes }">
		  <option value=${itemCodes }>${itemCodes }</option>
	  </c:forEach>
  </select>	  
  		
	<div>
	 <p><input type="submit" name="Delete"></p>	 
	</div>		
      
</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>