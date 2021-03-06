<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		      
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script type = "text/javascript" src = "jquery-3.2.1.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<link rel="stylesheet" href="css/accountcenter.css">
		<script src="script/accountcenter.js"></script>
		<script src="script/zxcvbn.js"></script>
		<title>Bookshelf | Account Center</title>
	</head>
	<body>
		<nav class="navbar navbar-default">
            <div class="container-fluid main-nav">
                <div class="wrapper">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="Index.jsp">bookshelf</a><p>| account center </p>
                    </div> 
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="Index.jsp">CONTINUE SHOPPING</a></li>
                        <li><a href="logout">LOGOUT</a></li>
                    </ul>  
                </div>
            </div>
        </nav>
    
        <div class="container-fluid" id="dashboard">
            <div class="row">
                <div class="col-sm-7" id="accdetails-div">
                    <h4>ACCOUNT DETAILS</h4>
                    
                    <form class="form-horizontal" id="form-editacc">
                        <div class="form-group">
                            <label class="control-label col-sm-3" for="name">Name: </label>
                            <div class="col-sm-9">
                                <input class="form-control editacc-fields" type="text" id="name" disabled>
                                <p class="warnings" id="name-error">Please enter your name</p>
                            </div>
                        </div>
                  
                        <!-- TO-DO: get user address from DB --> 
                         <div class="form-group combaddress-div">
                            <label class="control-label col-sm-3" for="comb-address">Address: </label>
                            <div class="col-sm-9">
                                <input class="form-control editacc-fields" type="text" id="comb-address" disabled>
                            </div>
                        </div>
                        
                        
                        <div class="form-group address-div">
                            <label class="control-label col-sm-3" for="st-address">Street Address: </label>
                            <div class="col-sm-9">
                                <input class="form-control address-fields" type="text" id="st-address" disabled>
                                <p class="warnings" id="st-error">Please enter your street address</p>
                            </div>
                        </div>
                        
                        <div class="form-group address-div">
                            <label class="control-label col-sm-3" for="city">City: </label>
                            <div class="col-sm-9">
                                <input class="form-control address-fields" type="text" id="city" disabled>
                                <p class="warnings" id="city-error">Please enter city</p>
                            </div>
                        </div>
                        
                        <div class="form-group address-div">
                            <label class="control-label col-sm-3" for="province">Province: </label>
                            <div class="col-sm-9">
                                <input class="form-control address-fields" type="text" id="province" disabled>
                                <p class="warnings" id="prov-error">Please enter province</p>
                            </div>
                        </div>
                        
                        <div class="form-group address-div">
                            <label class="control-label col-sm-3" for="postalcode">Postal Code: </label>
                            <div class="col-sm-9">
                                <input class="form-control address-fields" type="number" id="postalcode" disabled>
                                <p class="warnings" id="postal-error">Please enter valid postal code</p>
                            </div>
                        </div>
                        
                        <!-- end of address div -->
                        
                        <div class="form-group">
                            <label class="control-label col-sm-3" for="phone">Phone: </label>
                            <div class="col-sm-9">
                                <input class="form-control editacc-fields" type="number" id="phone" disabled>
                                <p class="warnings" id="mobile-error">Please enter valid mobile number</p>
                            </div>
                        </div>
                    </form>
                    <br>
                    <button class="btn btn-default" id="btn-editacc">EDIT ACCOUNT DETAILS</button>
                    <button class="btn btn-default" id="save-editacc">SAVE</button>
                </div>
                	<div class="col-sm-5" id="changepw-div">
	                    <h4>SECURITY</h4>                 
	                         <div class="form-group">
	                            <label class="control-label col-sm-4" for="pw">Password: </label>
	                            <div class="col-sm-8">
	                                <input class="form-control changepw-fields" disabled type="password" id="oldpw" value="nyeamchocnut">
	                            </div>
	                        </div>
	                        
	                        <div class="form-group newpw-div">
	                            <label class="control-label col-sm-4" for="pw">New Password: </label>
	                            <div class="col-sm-8">
	                                <input class="form-control changepw-fields" type="password" id="newpw">
	                                <meter max="4" id="password-strength-meter" low="2" optimum="4" high="3"></meter><br>
	                            </div>
	                        </div>
	                        <div class="form-group newpw-div">
	                            <label class="control-label col-sm-4" for="pw">Confirm New Password: </label>
	                            <div class="col-sm-8">
	                                <input class="form-control changepw-fields" type="password" id="newpw2">
	                            </div>
	                        </div>
	                    </form>
	                    
	                    <br>
	                    <p class="help-text">Password must be 8-20 characters long with at least 1 of the following: 
	                    uppercase, lowercase, numbers, and special characters (~!@#$%^*-_=+[{]}/;:,.?).</p>
	                    <button class="btn btn-default" id="btn-changepw">CHANGE PASSWORD</button>
	                    <button class="btn btn-default" id="save-changepw">SAVE</button>
	                </div>
            </div>
            <hr>
            <div class="row" id="orderhistory-div">
                <h4>ORDER HISTORY</h4>
                 <table class="table">
                    <thead>
                        <tr>
                            <th>ORDER ID</th>
                            <th>EMAIL ADDRESS</th>
                            <th>NAME</th>
                            <th>TOTAL</th>
                        </tr>
                    </thead>
                    <tbody id="orderdetail-ordertable"></tbody>
                </table>
            </div>
            
            <!-- ORDER DETAILS MODAL -->
            <div id="details-modal" class="modal" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <p class="detail-header">Order ID: #420420420</p>
                            <p class="detail-subheader">Status: Out for Delivery</p>
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
            </div>
        </div>
	</body>
</html>