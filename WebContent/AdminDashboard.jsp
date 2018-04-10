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
	    <script src = "script/adminDashboard.js"></script>
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
                         <a class="navbar-brand" href="Portal.jsp">bookshelf</a><p>| portal</p>
                   </div>

                   <div class="collapse navbar-collapse" id="nav_collapse">
                      <ul class="nav navbar-nav navbar-right">
                           <li class=dropdown>
                               <a class="dropdown-toggler" data-toggle="dropdown" href="#">ACCOUNT <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                   <li><a href="#">SETTINGS</a></li>
                                   <li><a href="logout">LOGOUT</a></li>
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
	                <li class="active"><a href="#ordertracking" data-toggle="tab">ORDER TRACKING</a></li>
	                <li><a href="#inventory" data-toggle="tab">INVENTORY</a></li>
	                <li><a href="#manage-accounts" data-toggle="tab">MANAGE ACCOUNTS</a></li>
	                <form action="printLog" method = "GET">
	                	 <li><button type="submit" class="btn btn-default" id="btn-log">DOWNLOAD LOG</button></li>
	                </form>
	            </ul>
	        </div>
	            
            <div class="col-sm-10 tab-content">
            	<!-- ORDER TRACKING TAB -->
                <div class="tab-pane" id="ordertracking">
                    <div class="input-group stylish-input-group">
                        <input type="text" class="form-control search-bar" placeholder="search" >
                        <span class="input-group-addon">
                            <button type="submit">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>  
                        </span>
                    </div>
                    
                    <div class="row table-div">
                        <div class="col-sm-12 orderdiv">
                            <table class="table" >
                                <thead>
                                    <tr>
                                        <th>ORDER ID</th>
                                        <th>EMAIL ADDRESS</th>
                                        <th>NAME</th>
                                        <th>TOTAL</th>
                                    </tr>
                                </thead>
                                <tbody id = "orderTable">
                                </tbody>
                            </table>
                        </div>
                    </div>
                    
                    <!-- ORDER DETAILS MODAL -->
                    <div id="details-modal" class="modal" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">ORDER ID:</h4>
                                </div>
                                <div class="modal-body" >
                                	<div class="row" id ="ordersummary-div">
                                	</div>
                                    <div class="row">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>PRODUCT</th>
                                                    <th>QTY</th>
                                                    <th>UNIT PRICE</th>
                                                    <th>TOTAL PRICE</th>
                                                </tr>
                                            </thead>
                                            <tbody id = "orderdetails-ordertable">
                                            </tbody>
                                        </table> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> <!--  END ORDER DETAILS -->
                </div> <!-- END TRACK ORDER -->
            
                <!-- INVENTORY TAB -->
                <div class="tab-pane" id="inventory">
                	<button type="button" class="btn btn-default" 
                	data-toggle="modal" data-target="#addbook-modal" id="btn-addbook">
                	<span class="glyphicon glyphicon-plus"></span>  ADD A BOOK</button>
                	
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
	                                       <button type="button" class="btn btn-default" data-toggle="modal" 
	                                       data-target="#addbook-div" id="btn-upload">
	                                       <span class="glyphicon glyphicon-cloud-upload"></span>  UPLOAD COVER</button>
	                                  </div>
									  <form action = "addBook" method = "POST">
									  	<div class="col-sm-8">
		                                      <div class="form-group">
		                                          <label for="title-inp">Title</label>
		                                          <input type="text" class="form-control" id="addbook-title" name = "bookTitle">
		                                      </div>
		                                      <div class="form-group">
		                                          <label for="author-inp">Author</label>
		                                          <input type="text" class="form-control" id="addbook-author" name = "selectAuthor">
		                                      </div>
		                                      <div class="form-group">
		                                          <label for="isbn-inp">ISBN</label>
		                                          <input type="number" class="form-control" id="addbook-isbn" name = "isbn">
		                                      </div>
		                                      <div class="form-group">
		                                          <label for="publisher-inp">Publisher</label>
		                                          <input type="text" class="form-control" id="addbook-publisher" name = "publisher">
		                                      </div>
		                                      <div class="form-group">
		                                          <label for="datepub-inp">Date Published</label>
		                                          <input type="date" class="form-control" id="addbook-published" name = "published">
		                                      </div>
		                                      
		                                      <div class="form-group">
		                                          <label for="genre-inp">Genre</label>
		                                          <select class="form-control" id="addbook-genre" name = "genre">
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
		                                              <input type="number" class="form-control" id="addbook-price" name = "price">
		                                          </div>  
		                                    </div>
		                                      
		                                    <div class="col-sm-1"></div>
		                                      
		                                    <div class="col-sm-5 abdiv-bottom">
		                                        <div class="form-group">
		                                            <label for="qty-inp">Qty</label>
		                                            <input type="number" class="form-control" id="addbook-qty" name = "qty">
		                                        </div>  
		                                    </div>
		                                    
		                                    <label class="radio-inline"><input type="radio" name="addbook-format" value = "Paperback">Paperback</label>
		                                    <label class="radio-inline"><input type="radio" name="addbook-format" value = "Hardbound">Hardbound</label>
		                                    <br>
		                                      
		                                    <button type="submit" class="btn btn-default btn-okay"  id="btn-okayinventory"> <span class="glyphicon glyphicon-ok"></span></button>
		                                  </div>
									  </form> 	
	                              </div>
	                            </div>
	                        </div>
	                	</div>
	            	</div> <!-- END ADD BOOK MODAL -->
                
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
                                <tbody id = "inventoryTable"></tbody>
                               </table>
                        </div>
                    </div> <!-- END INVENTORY TABLE -->	
                    
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
                                       <img src="css/generic-cover.jpg" class="img-responsive">
                                       <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addbook-div" id="btn-upload"><span class="glyphicon glyphicon-cloud-upload"></span>  UPLOAD COVER</button>
                                   </div>

                                   <div class="col-sm-8">
                                       <div class="form-group">
                                           <label for="title-inp">Title</label>
                                           <input type="text" class="form-control" id="titleField">
                                       </div>
                                       <div class="form-group">
                                           <label for="author-inp">Author</label>
                                           <input type="text" class="form-control" id="authorField">
                                       </div>
                                       <div class="form-group">
                                           <label for="isbn-inp">ISBN</label>
                                           <input type="number" class="form-control" id="isbnField">
                                       </div>
                                       <div class="form-group">
                                           <label for="publisher-inp">Publisher</label>
                                           <input type="text" class="form-control" id="publisherField">
                                       </div>
                                       <div class="form-group">
                                           <label for="datepub-inp">Date Published</label>
                                           <input type="date" class="form-control" id="publishedField">
                                       </div>


                                       <div class="form-group">
                                           <label for="genre-inp">Genre</label>
                                           <select class="form-control" id="genreList">
                                               <option value = "Biography and Memoir">Biography &amp; Memoir</option>
                                               <option value = "Business and Finance">Business &amp; Finance</option>
                                               <option value = "Computers">Computers</option>
                                               <option value = "Fiction and literature">Fiction &amp; Literature</option>
                                               <option value = "Graphic Novels and Manga">Graphic Novels &amp; Manga</option>
                                               <option value = "SciFi and Fantasy">Sci-fi &amp; Fantasy</option>
                                               <option value = "Science and Nature">Science &amp; Nature</option>
                                               <option value = "Travel">Travel</option>
                                           </select>
                                     </div>

                                     <div class="col-sm-3 abdiv-bottom">
                                          <div class="form-group">
                                               <label for="author-inp">Price</label>
                                               <input type="number" class="form-control" id="priceField">
                                           </div>  
                                     </div>
	
									<div class="col-sm-3 abdiv-bottom">
                                          <div class="form-group">
                                               <label for="author-inp">Stock</label>
                                               <input type="number" class="form-control" id="stockField">
                                           </div>  
                                     </div>
                                     
                                     <div class="col-sm-7 abdiv-bottom">
                                         <label class="radio-inline"><input type="radio" name="edit-format" id = "paperbackRadioEdit" value = "Paperback">Paperback</label>
                                         <label class="radio-inline"><input type="radio" name="edit-format" id = "hardboundRadioEdit" value = "Hardbound">Hardbound</label>
                                     </div>
                                     <br>
                                     <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addbook-div" id="btn-edit-okay"> <span class="glyphicon glyphicon-ok"></span></button>
                                   </div>
                               </div>
                             </div>
                         </div>
                       </div>
                     </div> <!-- END VIEW BOOK -->
                
                </div> <!-- END INVENTORY TAB -->
               
            	<!-- MANAGE ACCOUNTS TAB -->
            	<div class="tab-pane" id="manage-accounts">
            		 <button type="button" class="btn btn-default" data-toggle="modal" 
            		 data-target="#addaccount-modal" id="btn-addaccount">
            		 <span class="glyphicon glyphicon-plus"></span>  ADD ACCOUNT</button>
            		 
            		 <!-- ADD ACOUNT MODAL -->
                     <div id="addaccount-modal" class="modal" role="dialog">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header">
								    <button type="button" class="close" data-dismiss="modal">&times;</button>
								    <h4 class="modal-title">ADD A ACCOUNT</h4>
								</div>
						  
								<div class="modal-body">
									<div class="row">
									    <div class="form-group">
									        <label for="fname-inp">First Name</label>
									        <input type="text" class="form-control" id="fname-inp">
									    </div>
									    
									    <div class="form-group">
									        <label for="lname-inp">Last Name</label>
									        <input type="text" class="form-control" id="lname-inp">
									    </div>
									    
									    <div class="form-group">
									        <label for="email-inp">Email Address</label>
									        <input type="text" class="form-control" id="email-inp">
									    </div>
									    
									    <div class="form-group">
									        <label for="dept-inp">Department</label>
									        <select class="form-control" id="dept-inp">
									            <option>Customer Support</option>
									            <option>Shipping &amp; Tracking</option>
									            <option>Inventory</option> 
									        </select>
									  	</div>
									    
									  	<button type="button" class="btn btn-default btn-okay" id="btn-okayaccount">
									  	<span class="glyphicon glyphicon-ok"></span></button>
									</div>
								</div>
						  	</div>
						</div>
                    </div>
            		
					<!-- ACCOUNTS TABLE -->
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
                    </div> <!-- END OF ACCOUNTS TABLE -->
            		
            	</div> <!-- END MANAGE ACCOUNTS -->
            </div> <!-- END TAB-CONTENT -->
		</div>
	</body>
</html>