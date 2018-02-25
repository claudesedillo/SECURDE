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
                
        <link rel="stylesheet" href="css/admindashboard.css">
        
        <Title>Bookshelf Portal | Dashboard</Title>
</head>
<body>
	<nav class="navbar navbar-default" id="nav">
		<div class="container-fluid main-nav">
           <div class="wrapper">
                   <div class="navbar-header">
                       <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#nav_collapse">
                           <span class="icon-bar"></span>
                           <span class="icon-bar"></span>
                           <span class="icon-bar"></span> 
                       </button>
                         <a class="navbar-brand" href="portal.html">bookshelf</a><p>| portal</p>
                   </div>

                   <div class="collapse navbar-collapse" id="nav_collapse">
                      <ul class="nav navbar-nav navbar-right">
                           <li class=dropdown>
                               <a class="dropdown-toggler" data-toggle="dropdown" href="#">ACCOUNT <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                   <li><a href="#">SETTINGS</a></li>
                                   <li><a href="#">LOGOUT</a></li>
                               </ul>
                           </li>
                       </ul>
                   </div>
               </div>
            </div>
        </nav>
        
        <div class="container-fluid dashboard">
            <div class="col-sm-2" id="sidenav">
                <ul class="nav nav-pills nav-stacked">
                    <li><a href="#">INBOX</a></li>
                    <li><a href="#">ORDER TRACKING</a></li>
                    <li><a href="#">INVENTORY</a></li>
                </ul>
            </div>
            
            <div class="col-sm-10">
                <div class="inventory">
                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addbook-div" id="btn-addbook"><span class="glyphicon glyphicon-plus"></span>  ADD A BOOK</button>
                
                    <div id="addbook-div" class="modal" role="dialog">
                      <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">ADD A BOOK</h4>
                            </div>
                        
                            <div class="modal-body">
                              <div class="row">
                                  <div class="col-sm-4" id="cover-div">
                                      <img src="css/generic-cover.jpg" class="img-responsive">
                                       <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addbook-div" id="btn-upload">
                                       <span class="glyphicon glyphicon-cloud-upload"></span>  UPLOAD COVER</button>
                                  </div>
								<form action = "addBook" method = "POST">
									<div class="col-sm-8">
                                      <div class="form-group">
                                          <label for="title-inp">Title</label>
                                          <input type="text" class="form-control" id="title-inp" name = "bookTitle">
                                      </div>
                                      <div class="form-group">
                                          <label for="author-inp">Author</label>
                                          <input type="text" class="form-control" id="author-inp" name = "selectAuthor">
                                      </div>
                                      <div class="form-group">
                                          <label for="isbn-inp">ISBN</label>
                                          <input type="number" class="form-control" id="isbn-inp" name = "isbn">
                                      </div>
                                      <div class="form-group">
                                          <label for="publisher-inp">Publisher</label>
                                          <input type="text" class="form-control" id="publisher-inp" name = "publisher">
                                      </div>
                                      <div class="form-group">
                                          <label for="datepub-inp">Date Published</label>
                                          <input type="date" class="form-control" id="datepub-inp" name = "published">
                                      </div>
                                      
                                      
                                      <div class="form-group">
                                          <label for="genre-inp">Genre</label>
                                          <select class="form-control" id="genre-inp" name = "genre">
                                              <option>Biography &amp; Memoir</option>
                                              <option>Business &amp; Finance</option>
                                              <option>Computers</option>
                                              <option>Fiction &amp; Literature</option>
                                              <option>Graphic Novels &amp; Manga</option>
                                              <option>Sci-fi &amp; Fantasy</option>
                                              <option>Science &amp; Nature</option>
                                              <option>Travel</option>
                                          </select>
                                    </div>
                                      
                                    <div class="col-sm-3 abdiv-bottom">
                                         <div class="form-group">
                                              <label for="author-inp">Price</label>
                                              <input type="number" class="form-control" id="price-inp" name = "price">
                                          </div>  
                                    </div>
                                      
                                    <div class="col-sm-7 abdiv-bottom">
                                        <label class="radio-inline"><input type="radio" name="optradio" name = "format" >Paperback</label>
                                        <label class="radio-inline"><input type="radio" name="optradio" name = "format">Hardbound</label>
                                    </div>
                                    <br>
                                    <button type="submit" class="btn btn-default" data-toggle="modal" data-target="#addbook-div" id="btn-okay"> 
                                    <span class="glyphicon glyphicon-ok"></span></button>
                                  </div>
								</form>
                              </div>
                            </div>
                        </div>
                      </div>
                    </div>
                </div>
            </div>
        </div>
</body>
</html>