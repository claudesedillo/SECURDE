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
                
        <link rel="stylesheet" href="css/portal.css">
        <script src ="script/adminLogin.js"></script>
		<title>Bookshelf | Employee Portal</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<a class="title" href="Portal.jsp">bookshelf</a><p>| portal</p>
        </div>
        <hr>
        <div class="row">
              <div class="form-group">
                  <input type="email" class="form-control" placeholder="email address" id = "email-admin">
              </div>
               <div class="form-group">
                  <input type="password" class="form-control" placeholder="password" id = "password-admin">
              </div>
              <button type="submit" class="btn btn-default" id="btn-signin" name = "btn-signin" value = "admin-signin">sign in</button>
           <br><br>
        </div>
    </div>
</body>
</html>