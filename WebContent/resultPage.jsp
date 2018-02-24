<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type = "text/javascript" src = "jquery-3.2.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id = "catalog">
		<h1 id = "catalogHeader">Search Results for: ${searchTerm}</h1>
		<c:if test =  "${not empty bookList}">
			<c:forEach items="${bookList}" var="b">
				<div class = "bookDiv">
					<p>${b.title}</p>
					<p>${b.isbn}</p>
					<p>${b.genre}</p>
					<p>${b.format}</p>
					<p>${b.price}</p>
				</div>
			</c:forEach>
		</c:if>
		<c:if test = "${empty bookList}">
			<p id="notFound">No books found!</p>
		</c:if>
	</div>
</body>
</html>