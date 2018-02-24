<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EDIT</title>
</head>
<body>
<form action="editAdminConfirm" method="post">
	EDIT Admin<br>
	Email :		<input type="text" name="email" value="<%=request.getAttribute("email")%>"> <br>
	Password :	<input type="text" name="pass" value="<%=request.getAttribute("pass")%>"> <br>
	Firstname :	<input type="text" name="first" value="<%=request.getAttribute("first")%>"> <br>
	Lastname :	<input type="text" name="last" value="<%=request.getAttribute("last")%>"> <br>
	Role :		<input type="text" name="role" value="<%=request.getAttribute("role")%>"> <br>
 	<button type="submit" name = "btn-confirm" value="confirm">Confirm</button> 
</form>
</body>
</html>