<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EDIT</title>
</head>
<body>
<form action="editConfirm" method="post">
	EDIT PRODUCT<br>
	Title :		<input type="text" name="title" value="<%=request.getAttribute("title")%>"> <br>
	ISBN :		<input type="text" name="isbn" value="<%=request.getAttribute("isbn")%>"> <br>
	GENRE :		<input type="text" name="genre" value="<%=request.getAttribute("genre")%>"> <br>
	Format :	<input type="text" name="format" value="<%=request.getAttribute("format")%>"> <br>
	Published :	<input type="Date" name="pub" value="<%=request.getAttribute("pub")%>"> <br>
	Price :		<input type="float" name="price" value="<%=request.getAttribute("price")%>"> <br>
 	Stock :		<input type="int" name="stock" value="<%=request.getAttribute("stock")%>"> <br>
 	<button type="submit" name = "btn-confirm" value="confirm">Confirm</button> 
</form>
</body>
</html>