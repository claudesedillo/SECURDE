<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	        
	        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	        <script type = "text/javascript" src = "jquery-3.2.1.min.js"></script>
	        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	        
	        <link rel="stylesheet" href="css/index.css">
	        <title>Login Sample</title>
	</head>
	
	<body>
	        <nav class="navbar navbar-default">
	            <div class="container-fluid signup-bar">
	                DON'T HAVE AN ACCOUNT? <a href="#">SIGN UP</a> NOW!
	            </div>
	            <div class="container-fluid ss-bar">
	                <div class="col-sm-8">
	                    <button type="button" class="btn btn-default btn-navsignin" data-toggle="modal" data-target="#signup-modal"> SIGN IN</button>
	                </div>
	                
	                <!-- SIGN UP MODAL -->
	                <div id="signup-modal" class="modal" role="dialog">
	                    <div class="modal-dialog">
	                        <div class="modal-content">
	                            <div class="modal-body">
	                                <button type="button" class="close" data-dismiss="modal">&times;</button>
	                                <h4>SIGN IN!</h4>
	                                Don't have an account? <a href="#">Create one in less that one minute!</a>
	                                <br><br>
	                                <form>
	                                    <div class="form-group">
	                                        <input type="email" class="form-control" id="email" placeholder="email address">
	                                    </div>
	                                    <div class="form-group">
	                                        <input type="password" class="form-control" id="password" placeholder="password">
	                                    </div>
	                                </form>
	                                <button type="button" class="btn btn-default btn-signin">SIGN IN</button>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="col-sm-3">
	                    <div class="input-group stylish-input-group">
	                        <input type="text" class="form-control search-bar"  placeholder="search" >
	                        <span class="input-group-addon">
	                            <button type="submit">
	                                <span class="glyphicon glyphicon-search"></span>
	                            </button>  
	                        </span>
	                    </div>
	                </div>
	                <div class="col-sm-1">
	                     <button type="button" class="btn btn-default btn-cart"><span class="glyphicon glyphicon-shopping-cart"></span><span class="badge cart-badge">0</span></button>
	                </div>
	            </div>
	            
	            <div class="container-fluid main-nav">
	                <div class="wrapper">
	                    <div class="navbar-header">
	                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#nav_collapse">
	                            <span class="icon-bar"></span>
	                            <span class="icon-bar"></span>
	                            <span class="icon-bar"></span> 
	                        </button>
	                        <a class="navbar-brand" href="#">bookshelf</a>
	                    </div>
	
	                    <div class="collapse navbar-collapse" id="nav_collapse">
	                       <ul class="nav navbar-nav navbar-right">
	                            <li><a href="#">SHOP BY GENRE</a></li>
	                            <li><a href="#">BEST SELLERS</a></li>
	                            <li><a href="#">SALE</a></li>
	                        </ul>
	                    </div>
	                </div>
	              </div>
	        </nav>
	        <br>
	        <div class="container-fluid feed">
	           
	            
	        </div>
	        
	        <br>
	        
	        <footer class="page-footer center-on-small-only"> 
	            <div class="container-fluid foot-section">
	                <div class="col-md-4">
	                    <h5>CUSTOMER SERVICES</h5>
	                    <ul>
	                        <li><a href="#">help</a></li>
	                        <li><a href="#">contact us</a></li>
	                        <li><a href="#">order tracking</a></li>
	                        <li><a href="#">shipping  returns</a></li>
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