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
        
        <link rel="stylesheet" href="css/signup.css">
        <link rel="stylesheet" href="css/nav.css">
        <link rel="stylesheet" href="css/footer.css">
        <script src="script/nav.js"></script>
        <script src="script/zxcvbn.js"></script>
        <script src ="script/signup.js"></script>
        <title>Bookshelf | Sign Up</title>
    </head>
    
     <body onload="getCartCount()">
        <nav class="navbar navbar-default" id="nav"></nav><br>
        
        <div class="container-fluid feed">
            <h3>create your bookshelf account now!</h3>
            <p>Access your Bookshelf account, write reviews, track orders and more.</p>
            <hr><br>
                <div class="col-sm-6">
                    <div class="form-group">
                        <input type="text" class="form-control" id="fname" placeholder="first name">
                        <p class="warnings" id="fname-error">Please enter your first name.</p>
                    </div>
                </div>
                <div class="col-sm-5 col-sm-offset-1">
                    <div class="form-group">
                        <input type="text" class="form-control" id="lname" placeholder="last name">
                        <p class="warnings" id="lname-error">Please enter your last name.</p>
                    </div>
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" id="email-signup" placeholder="email address">
                    <p class="warnings" id="userexists">Email address already in use.</p>
                    <p class="warnings" id="email-error">Please enter valid email address.</p>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password-signup" placeholder="password" maxlength="20">
                    <meter max="4" id="password-strength-meter" low="2" optimum="4" high="3"></meter>
                    <p class="warnings" id="pw-error">Please enter valid password.</p>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="confirm-pw" placeholder="confirm password" maxlength="20">
                    <p class="warnings" id="pwnotmatch">Passwords do not match.</p>
                </div>
                <div class="form-group">
                    <select class="form-control" id="security-qs" >
                        <option selected disabled>security question</option>
                        <option>what’s your dream car?</option>
                        <option>what subject did you enjoy the most in college?</option>
                        <option>what's your dream travel destination?</option>
                        <option>what's the name of your first pet?</option>
                        <option>what's your usual coffee order?</option>
                    </select>
                    <p class="warnings" id="sq-error">Please select valid option.</p>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="security-answer" placeholder="security answer">
                    <p class="warnings" id="sa-error">Please enter valid security answer.</p>
                </div>
                <hr>
                 <p class="help-text">Password must be 8-20 characters long with at least 3 of the following: uppercase, lowercase, numbers, 
                 and special characters (~!@#$%^*-_=+[{]}/;:,.?).</p><br>
                
                <button class="btn btn-default" id="btn-signup" name="btn-signup" value="cust-signup">SIGN UP</button>   
        </div>
        <br><br> 
       <footer class="page-footer center-on-small-only" id="footer"></footer>
      
    </body>
</html>