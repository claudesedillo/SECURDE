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
        
        <link rel="stylesheet" href="css/viewbygenre.css">
        <link rel="stylesheet" href="css/nav.css">
        <link rel="stylesheet" href="css/footer.css">
        <script src="script/viewbygenre.js"></script>
</head>
<body>
 		<nav class="navbar navbar-default" id="nav"></nav>
        
        <!--BODY-->
        <div class="container-fluid" id="feed">
          <!--   <div class="row banner">
                <p>DEALS OF THE WEEK</p>
                <a href="#">ONLINE ONLY <span class="glyphicon glyphicon-play"></span></a>
            </div> -->
            
            <div class="col-sm-2" id="filters-div">
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
            
            <div class="col-sm-10" id="searchresult-div">
                <h5>"GENRE" BOOKS</h5>
                
                <div class="col-sm-3 book-div">
                    <img src="css/generic-cover.jpg" class="img-responsive">
                    <div class="row">
                        <p class="title">NO TITLE YET</p>
                        <p class="author">No Author</p>
                        <p class="price">P420.00</p>
                    </div>
                </div>
                
                <div class="col-sm-3 book-div">
                    <img src="css/generic-cover.jpg" class="img-responsive">
                    <div class="row">
                        <p class="title">NO TITLE YET</p>
                        <p class="author">No Author</p>
                        <p class="price">P420.00</p>
                    </div>
                </div>
                
                <div class="col-sm-3 book-div">
                    <img src="css/generic-cover.jpg" class="img-responsive">
                    <div class="row">
                        <p class="title">NO TITLE YET</p>
                        <p class="author">No Author</p>
                        <p class="price">P420.00</p>
                    </div>
                </div>
                
                <div class="col-sm-3 book-div">
                    <img src="css/generic-cover.jpg" class="img-responsive">
                    <div class="row">
                        <p class="title">NO TITLE YET</p>
                        <p class="author">No Author</p>
                        <p class="price">P420.00</p>
                    </div>
                </div>
                
                <div class="col-sm-3 book-div">
                    <img src="css/generic-cover.jpg" class="img-responsive">
                    <div class="row">
                        <p class="title">NO TITLE YET</p>
                        <p class="author">No Author</p>
                        <p class="price">P420.00</p>
                    </div>
                </div>
            </div>
        </div>
        <br><br>
        <footer class="page-footer center-on-small-only" id="footer"></footer>
</body>
</html>