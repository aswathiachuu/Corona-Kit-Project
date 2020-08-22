<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Home</title>
</head>
<body>
<div>
<jsp:include page="header.jsp"/>
<hr/>
<div>
	<a href="user?action=newuser"><h3>Click here to Order a new Corona  Prevention Kit</h3></a> 
</div>

	<h4>Admin Login</h4>
	<%
	      String user = (String) getServletContext().getAttribute("access");
	      String se = (String) request.getSession().getAttribute("User");
	      
	%>
	
	<%
			if(se == null )
			{
	%>
	<form action="admin?action=login" method="post">
		<div>
			<div><label for="loginid">Enter login Id</label> </div>
			<div><input type="text" id="loginid" name="loginid"> </div>
		</div>
		<div>
			<div><label for="password">Enter password</label> </div>
			<div><input type="text" id="password" name="password"> </div>
		</div>
		<div>
			<div><input type="submit" value="Login"> </div>
		</div>
	</form>
</div>
<hr/>


<%
			}
			else 
			{
				%>
				
			<p> <%=se %> logged In</p>	
				
	<% 							
			}
	
	%>
	
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>