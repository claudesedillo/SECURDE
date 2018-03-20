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
		<title>Bookshelf | Account Center</title>
	</head>
	<body>
		<nav class="navbar navbar-default">
            <div class="container-fluid main-nav">
                <div class="wrapper">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="index.html">bookshelf</a><p>| account center </p>
                    </div> 
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="index.html">CONTINUE SHOPPING</a></li>
                        <li><a href="#">LOGOUT</a></li>
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
                            <label class="control-label col-sm-2" for="name">Name: </label>
                            <div class="col-sm-10">
                                <input class="form-control editacc-fields" type="text" id="name" value="Not Specified" disabled>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="email">Email: </label>
                            <div class="col-sm-10">
                                <input class="form-control editacc-fields" type="text" id="email" value="jessganoww@gmail.com" disabled>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="address">Address: </label>
                            <div class="col-sm-10">
                                <input class="form-control editacc-fields" type="text" id="address" value="The Grange, Edmonton Alberta CA" disabled>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="phone">Phone: </label>
                            <div class="col-sm-10">
                                <input class="form-control editacc-fields" type="number" id="phone" value="09989716071" disabled>
                            </div>
                        </div>
                    </form>
                    <br>
                    <button class="btn btn-default" id="btn-editacc">EDIT ACCOUNT DETAILS</button>
                    <button class="btn btn-default" id="save-editacc">SAVE</button>
                </div>
                <div class="col-sm-5" id="changepw-div">
                    <h4>SECURITY</h4>
                    
                    <form class="form-horizontal" id="form-changepw">
                         <div class="form-group">
                            <label class="control-label col-sm-4" for="pw">Password: </label>
                            <div class="col-sm-8">
                                <input class="form-control changepw-fields" type="password" id="oldpw" value="nyeamchocnut" disabled>
                            </div>
                        </div>
                        
                        <div class="form-group newpw-div">
                            <label class="control-label col-sm-4" for="pw">New Password: </label>
                            <div class="col-sm-8">
                                <input class="form-control changepw-fields" type="password" id="newpw" disabled>
                            </div>
                        </div>
                        <div class="form-group newpw-div">
                            <label class="control-label col-sm-4" for="pw">Confirm New Password: </label>
                            <div class="col-sm-8">
                                <input class="form-control changepw-fields" type="password" id="newpw2" disabled>
                            </div>
                        </div>
                    </form>
                    <br>
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
	</body>
</html>