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
        
        <link rel="stylesheet" href="css/searchresult.css">
        <link rel="stylesheet" href="css/nav.css">
        <link rel="stylesheet" href="css/footer.css">
        <script src="script/searchresult.js"></script>
        <script type = "text/javascript" src= "script/searchOptions.js"></script>
        <Title>Bookshelf | Search</Title>
</head>
<body>
  		<nav class="navbar navbar-default" id="nav"></nav>
        
        <!--BODY-->
        <div class="container-fluid" id="feed">
            <div class="col-sm-3" id="filters-div">
                <h5>FILTERS:</h5>
                <p>FORMAT</p>
                <div class="checkbox">
                  <label><input type="checkbox" value="">Paperback</label>
                </div>
                <div class="checkbox">
                  <label><input type="checkbox" value="">Hardbound</label>
                </div>
                <br>
                 <p>PRICE RANGE</p>
                <div class="checkbox">
                  <label><input type="checkbox" value="">Under ₱500</label>
                </div>
                <div class="checkbox">
                  <label><input type="checkbox" value="">₱500 - ₱1000</label>
                </div>
                 <div class="checkbox">
                  <label><input type="checkbox" value="">Over ₱1000</label>
                </div>
                <br>
                <button type="button" class="btn btn-default" id="btn-clearfilter">CLEAR FILTERS</button> 
            </div>
            
            <div class="col-sm-9" id="searchresult-div">
                <h5>RESULTS FOR "${searchTerm}"</h5>
               	<c:if test =  "${not empty bookList}">
					<c:forEach items="${bookList}" var="b">
						<div class="col-sm-3 book-div">
                     		<div class="col-sm-3">
                          		<img src="css/generic-cover.jpg" class="img-responsive">
                     		</div>
                     <div class="col-sm-9">
                        <p class="title">${b.title}</p>
                        <p class="author">No Author</p>
                        <p class="price">${b.price}</p>
                       <button type="button" class="btn btn-default" id="btn-addtocart">ADD TO CART</button>
                     </div>
                </div>
					</c:forEach>
				</c:if>
 <!--           <div class="col-sm-3 book-div">
                     <div class="col-sm-3">
                          <img src="css/generic-cover.jpg" class="img-responsive">
                     </div>
                     <div class="col-sm-9">
                        <p class="title">NO TITLE YET</p>
                        <p class="author">No Author</p>
                        <p class="price">P420.00</p>
                       <button type="button" class="btn btn-default" id="btn-addtocard">ADD TO CART</button>
                     </div>
                </div>
                
                <div class="col-sm-3 book-div">
                     <div class="col-sm-3">
                          <img src="css/generic-cover.jpg" class="img-responsive">
                     </div>
                     <div class="col-sm-9">
                        <p class="title">NO TITLE YET</p>
                        <p class="author">No Author</p>
                        <p class="price">P420.00</p>
                       <button type="button" class="btn btn-default btn-addbook">ADD TO CART</button>
                     </div>
                </div> -->  
            </div>
                 
        </div>
        <br><br>
        <footer class="page-footer center-on-small-only" id="footer"></footer>
        
</body>
</html>