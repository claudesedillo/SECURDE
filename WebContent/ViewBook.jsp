<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <script src="script/viewbook.js"></script>
</head>
<body>
  <nav class="navbar navbar-default" id="nav"></nav><br>
        
        <div class="container-fluid feed">
            <div class="col-sm-5">
                <img src="cmbyn.jpeg" class="img-responsive">
            </div>
            
            <div class="col-sm-7">
                <p class="booktitle">CALL ME BY YOUR NAME</p>
                <p class="author">André Aciman</p><br>
                
                <!-- BOOK DETAILS -->
                <p class="labels">Genre: </p><p class="info" id="genre">Fiction</p><br>
                <p class="labels">Format: </p><p class="info" id="format">Paperback</p><br>
                <p class="labels">Publisher: </p><p class="info" id="publisher">Penguin</p><br>
                <p class="labels">Published: </p><p class="info" id="datepub">October 3, 2017</p><br>
                <p class="labels">ISBN: </p><p class="info" id="isbn">1250169445</p><br><br>
                <hr>
                <div class="row">
                    <p id="price">P420.00</p>
                    
                    <button type="button" class="btn btn-default" id="btn-addtocart"><span class="glyphicon glyphicon-shopping-cart"></span> Add To Cart </button>
                </div>
            </div>
        </div>
        <br><br>
        
          <footer class="page-footer center-on-small-only"> 
            <div class="container-fluid foot-section">
                <div class="col-md-4">
                    <h5>CUSTOMER SERVICES</h5>
                    <ul>
                        <li><a href="#">help</a></li>
                        <li><a href="#">contact us</a></li>
                        <li><a href="#">order tracking</a></li>
                        <li><a href="#">shipping &amp; returns</a></li>
                        <li><a href="#">feedback</a></li>
                    </ul>
                </div>

                <div class="col-md-8">
                    <br>
                    <i class="fa fa-facebook-square"></i>
                    <i class="fa fa-google-plus-square"></i>
                    <i class="fa fa-twitter-square"></i>
                    <br>
                    123 Somewhere Road, Over There City, That Country
                    <br>
                    © Bookshelf 2018. All Rights Reserved.
                </div>
            </div>
        </footer>


</body>
</html>