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
                                   <li><a href="Index.jsp">LOGOUT</a></li>
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
                    <li><a href="#ordertracking" data-toggle="tab">ORDER TRACKING</a></li>
                    <li><a href="#inventory" data-toggle="tab">INVENTORY</a></li>
                    <li class="active"><a href="#manage-accounts" data-toggle="tab">MANAGE ACCOUNTS</a></li>
                </ul>
            </div>
            
            <div class="col-sm-10 tab-content">
            	<!-- ORDER TRACKING TAB -->
                <div class="tab-pane" id="ordertracking">
                    <div class="input-group stylish-input-group">
                        <input type="text" class="form-control search-bar"  placeholder="search" >
                        <span class="input-group-addon">
                            <button type="submit">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>  
                        </span>
                    </div>
                    
                    <div class="row table-div">
                        <div class="col-sm-12 orderdiv">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>ORDER ID</th>
                                        <th>EMAIL ADDRESS</th>
                                        <th>NAME</th>
                                        <th>TOTAL</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><a data-toggle="modal" data-target="#">#420420420</a></td>
                                        <td>tobi@god.com</td>
                                        <td>Patrick Tobias</td>
                                        <td>₱450.00</td>
                                    </tr>
                                    <tr>
                                        <td><a href="#">#420420421</a></td>
                                        <td>claude@god.com</td>
                                        <td>Claude Sedillo</td>
                                        <td>₱450.00</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            
                <!-- INVENTORY TAB -->
                <div class="tab-pane" id="inventory">
                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addbook-modal" id="btn-addbook"><span class="glyphicon glyphicon-plus"></span>  ADD A BOOK</button>
                    
                    <!-- ADD BOOK MODAL -->
                  <div id="addbook-modal" class="modal" role="dialog">
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
                                       <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addbook-div" id="btn-upload"><span class="glyphicon glyphicon-cloud-upload"></span>  UPLOAD COVER</button>
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
	                                      
	                                    <div class="col-sm-6 abdiv-bottom">
	                                         <div class="form-group">
	                                              <label for="price-inp">Price</label>
	                                              <input type="number" class="form-control" id="price-inp" name = "price">
	                                          </div>  
	                                    </div>
	                                      
	                                    <div class="col-sm-1"></div>
	                                      
	                                    <div class="col-sm-5 abdiv-bottom">
	                                        <div class="form-group">
	                                            <label for="qty-inp">Qty</label>
	                                            <input type="number" class="form-control" id="qty-inp" name = "qty">
	                                        </div>  
	                                    </div>
	                                    
	                                    <label class="radio-inline"><input type="radio" name="optradio">Paperback</label>
	                                    <label class="radio-inline"><input type="radio" name="optradio">Hardbound</label>
	                                    <br>
	                                      
	                                    <button type="submit" class="btn btn-default btn-okay"  id="btn-okayinventory"> <span class="glyphicon glyphicon-ok"></span></button>
	                                  </div>
								  </form> 	
                              </div>
                            </div>
                        </div>
                      </div>
                    </div>
                    
                    <!-- INVENTORY TABLE -->
                    <div class="row table-div">
                        <div class="col-sm-12 bookdiv">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>TITLE</th>
                                        <th>AUTHOR</th>
                                        <th>FORMAT</th>
                                        <th>PRICE</th>
                                        <th>QTY</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><a data-toggle="modal" data-target="#viewbook-div">How To Be A God</a></td>
                                        <td>Patrick Tobias</td>
                                        <td>Hardbound</td>
                                        <td>₱450.00</td>
                                        <td>0</td>
                                    </tr>
                                    <tr>
                                        <td><a href="#">Guide To Ontario</a></td>
                                        <td>Claude Sedillo</td>
                                        <td>Paperback</td>
                                        <td>₱450.00</td>
                                        <td>5</td>
                                    </tr>
                                </tbody>
                            </table>
                            
                            <!--  VIEW BOOK -->
                             <div id="viewbook-div" class="modal" role="dialog">
                                  <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">VIEW BOOK</h4>
                                        </div>

                                        <div class="modal-body">
                                          <div class="row">
                                              <div class="col-sm-4" id="cover-div">
                                                  <img src="generic-cover.jpg" class="img-responsive">
                                                   <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addbook-div" id="btn-upload"><span class="glyphicon glyphicon-cloud-upload"></span>  UPLOAD COVER</button>
                                              </div>

                                              <div class="col-sm-8">
                                                  <div class="form-group">
                                                      <label for="title-inp">Title</label>
                                                      <input type="text" class="form-control" id="title-inp">
                                                  </div>
                                                  <div class="form-group">
                                                      <label for="author-inp">Author</label>
                                                      <input type="text" class="form-control" id="author-inp">
                                                  </div>
                                                  <div class="form-group">
                                                      <label for="isbn-inp">ISBN</label>
                                                      <input type="number" class="form-control" id="isbn-inp">
                                                  </div>
                                                  <div class="form-group">
                                                      <label for="publisher-inp">Publisher</label>
                                                      <input type="text" class="form-control" id="publisher-inp">
                                                  </div>
                                                  <div class="form-group">
                                                      <label for="datepub-inp">Date Published</label>
                                                      <input type="date" class="form-control" id="datepub-inp">
                                                  </div>


                                                  <div class="form-group">
                                                      <label for="genre-inp">Genre</label>
                                                      <select class="form-control" id="genre-inp">
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
                                                          <input type="number" class="form-control" id="price-inp">
                                                      </div>  
                                                </div>

                                                <div class="col-sm-7 abdiv-bottom">
                                                    <label class="radio-inline"><input type="radio" name="optradio">Paperback</label>
                                                    <label class="radio-inline"><input type="radio" name="optradio">Hardbound</label>
                                                </div>
                                                <br>
                                                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addbook-div" id="btn-okay"> <span class="glyphicon glyphicon-ok"></span></button>
                                              </div>
                                          </div>
                                        </div>
                                    </div>
                                  </div>
                                </div>
                            
                            
                            
                        </div>
                    </div>   
                </div>
                
                <!-- MANAGE ACCOUNTS TAB -->
                <div class="tab-pane active" id="manage-accounts">
                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addaccount-modal" id="btn-addaccount"><span class="glyphicon glyphicon-plus"></span>  ADD ACCOUNT</button>
                    
                    <!-- ADD ACOUNT MODAL -->
                    <div id="addaccount-modal" class="modal" role="dialog">
                      <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">ADD AN ACCOUNT</h4>
                            </div>
                        
                        	<form action = "signup" method = "POST">
	                            <div class="modal-body">
	                              <div class="row">
	                                  <div class="form-group">
	                                      <label for="fname-inp">First Name</label>
	                                      <input type="text" class="form-control" id="fname-inp" name = "firstName">
	                                  </div>
	                                  
	                                  <div class="form-group">
	                                      <label for="lname-inp">Last Name</label>
	                                      <input type="text" class="form-control" id="lname-inp" name = "lastName">
	                                  </div>
	                                  
	                                  <div class="form-group">
	                                      <label for="email-inp">Email Address</label>
	                                      <input type="email" class="form-control" id="email-inp" name ="email">
	                                  </div>
	                                  
	                                  <div class="form-group">
	                                      <label for="dept-inp">Department</label>
	                                      <select class="form-control" id="dept-inp" select name = "role">
	                                          <option>Customer Support</option>
	                                          <option>Shipping &amp; Tracking</option>
	                                          <option>Inventory</option> 
	                                      </select>
	                                </div>
	                                  
	                                <button type="submit" class="btn btn-default btn-okay" id="btn-okayaccount" name ="btn-signup" value ="admin-signup"> <span class="glyphicon glyphicon-ok"></span></button>
	                              </div>
	                            </div>
	                    	</form>
                        </div>
                      </div>
                    </div>
            
                   <div class="row table-div">
                        <div class="col-sm-12">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>LAST NAME</th>
                                        <th>FIRST NAME</th>
                                        <th>EMAIL ADDRESS</th>
                                        <th>ROLE</th>
                                        <th> </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Tobias</td>
                                        <td>Patrick</td>
                                        <td>tobi@god.com</td>
                                        <td>God</td>
                                        <td><button class="btn btn-default btn-tabledelacc" data-toggle="modal" data-target="#delaccount-modal"><span class="glyphicon glyphicon-remove"></span></button></td>
                                    </tr>
                                    <tr>
                                        <td>Sedillo</td>
                                        <td>Claude</td>
                                        <td>claude@god.com</td>
                                        <td>Apostle</td>
                                        <td><button class="btn btn-default btn-tabledelacc" data-toggle="modal" data-target="#delaccount-modal"><span class="glyphicon glyphicon-remove"></span></button></td>
                                    </tr>
                                </tbody>
                            </table>
                            
                            <div id="delaccount-modal" class="modal" role="dialog">
                              <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">DELETE ACCOUNT</h4>
                                    </div>

                                    <div class="modal-body">
                                        <div class="row">
                                            Are you sure you want to delete account: *insert user here*?
                                        </div>
                                    </div>    
                                    
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">CANCEL</button>
                                        <button type="button" class="btn btn-default btn-confirmdelete" >DELETE</button>   
                                    </div>
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