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
       
        <link rel="stylesheet" href="css/resetpassword.css">
		<script src="script/zxcvbn.js"></script>
        <script src ="script/resetPassword.js"></script>
		<title>Bookshelf | Password Recovery</title>
	</head>
	<body>
		<nav class="navbar navbar-default">
        	<div class="container-fluid main-nav">
                <div class="wrapper">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="Index.html">bookshelf</a>
                    </div>
                    <p class="navbar-text">PASSWORD RECOVERY</p>
                </div>
            </div>
        </nav>
                
        <div class="container-fluid" id="body">
            <div class="wrapper">
                <h3>RESET PASSWORD</h3><br>
                <form action = "newPasswordConfirm" method = "POST">
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="new password" name = "pass" id="password-forgot">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="confirm new password" name = "pass2">
                    </div>
                    <div class="form-group">
	                	<meter max="4" id="password-strength-meter" low="2" optimum="4" high="3"></meter>
	                </div>
                    <button type="submit" class="btn btn-default" id="btn-submit">SUBMIT</button>
                </form>
            </div>
        </div>
	</body>
</html>