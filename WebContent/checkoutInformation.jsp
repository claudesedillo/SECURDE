<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkout</title>
</head>
<body>
	<form action="checkoutConfirm" method="get">
		Confirm Checkout<br>
		Email :			 <input type="text" name="email"> <br>
		Street Address : <input type="text" name="streetAddress"> <br>
		City :			 <input type="text" name="city"> <br>
		Province :		 <input type="text" name="province"> <br>
		Postal Code :    <input type="int" name="postalcode"> <br>
		Phone Number :	 <input type="text" name="phoneNumber"> <br>
	 	<button type="submit" name = "btn-confirm" value="confirm">Confirm</button> 
	</form>
</body>
</html>