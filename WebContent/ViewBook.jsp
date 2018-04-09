<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	        
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	    
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	    
	    <link rel="stylesheet" href="css/viewbook.css">
	    <link rel="stylesheet" href="css/nav.css">
	    <link rel="stylesheet" href="css/footer.css">
	    <script src="script/searchOptions.js"></script>
	    <script type = "text/javascript" src = "script/nav.js"></script>
	    <script type = "text/javascript" src = "script/viewbook.js"></script>
	</head>
	<body onload="getCartCount()">
		<nav class="navbar navbar-default" id="nav"></nav><br>
		      
		      <div class="container-fluid" id="feed">
		         <div class="col-sm-4" id="pic-div">
		              <img src="css/generic-cover.jpg" class="img-responsive">
		         </div>
		          
		         <div class="col-sm-8" id="info-div">
		              <p id="booktitle">${book.title}</p>
		         <p id="author">${authorName}</p><br>
		         
		         <!-- BOOK DETAILS -->
		         <p class="labels">Genre: </p> <p class="info" id="genre">${book.genre}</p><br>
		         <p class="labels">Format: </p> <p class="info" id="format">${book.format}</p><br>
		         <p class="labels">Publisher: </p> <p class="info" id="publisher">${publisherName}</p><br>
		         <p class="labels">Published: </p> <p class="info" id="datepub">${book.publishedDate}</p><br>
		         <p class="labels">ISBN: </p> <p class="info" id="isbn">${book.isbn}</p><br><br>
		         <hr>
		         <div class="row">
		            <p id="price">P${book.price}</p>
		             

		             	<button class="btn btn-default" id="btn-addtocart" value="${book.bookID}"><span class="glyphicon glyphicon-shopping-cart"></span> Add To Cart </button>
	
		         </div>
		     </div>
		 </div>
		 <br><br>
		<footer class="page-footer center-on-small-only" id="footer"></footer>
	</body>
</html>