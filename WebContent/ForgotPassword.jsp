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
        
        <link rel="stylesheet" href="css/forgotpassword.css">
        <link rel="stylesheet" href="css/footer.css">
        <script src="script/forgotpw.js"></script>

<title>Bookshelf | Password Recovery</title>
</head>
<body>
 <nav class="navbar navbar-default">
            <div class="container-fluid main-nav">
                <div class="wrapper">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="Index.jsp">bookshelf</a>
                    </div>
                    
                    <!-- ADD ARROW -->
                    <a href="index.html" id="btn-home">BACK TO HOMEPAGE</a>
                </div>
            </div>
        </nav>
        
        <div class="container-fluid" id="body">
            <div class="wrapper">
                <h2>FORGOT PASSWORD</h2>
                <p>Reset your password by entering the email address associated with your account (please note that the email address must be listed in our system for you to receive the email).</p><br>

                <form>
                    <div class="form-group">
                        <input type="email" class="form-control" placeholder="email address">
                    </div>
                    
                    <p class="errormsg">Invalid username and/or password</p>

                    <button class="btn btn-default" id="btn-submit">SUBMIT</button>
                </form>
            </div>
        </div>
    
        <br><br>
        <footer class="page-footer center-on-small-only" id="footer"></footer>
</body>
</html>