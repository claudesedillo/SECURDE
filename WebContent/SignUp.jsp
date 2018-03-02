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
        <title>Bookshelf | Sign Up</title>
    </head>
    
     <body>
        <nav class="navbar navbar-default" id="nav"></nav><br>
        <div class="container-fluid feed">
            <h3>create your bookshelf account now!</h3>
            <p>Access your Bookshelf account, write reviews, track orders and more.</p>
            <hr><br>
            <form action="signup" method="post">
                <div class="form-group">
                    <input type="email" class="form-control" id="email" placeholder="Email Address" name ="email">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" placeholder="Password" name = "password">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="confirm-pw" placeholder="Confirm Password" name = "password2">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="firstname" placeholder="First Name" name ="firstname">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="lastname" placeholder="Last Name" name ="lastname">
                </div>
                 <div class="form-group">
                    <select class="form-control" id="security-qs" name = "securityQ">
                        <option selected disabled>security question</option>
                        <option>what’s your dream car?</option>
                        <option>what subject did you enjoy the most in college?</option>
                        <option>what's your dream travel destination?</option>
                        <option>what's the name of your first pet?</option>
                        <option>what's your usual coffee order?</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="security-answer" placeholder="Security Answer" name = "securityA">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="streetaddress" placeholder="Street Address" name ="streetaddress">
                </div>
                <div class="form-group">
                    <input type="number" class="form-control" id="postal" placeholder="Postal Code" name ="postal">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="city" placeholder="City" name ="city">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="province" placeholder="Province" name ="province">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="phone" placeholder="Phone Number" name ="phone">
                </div>
                <hr>
                 <p class="help-text">Password must be 8-20 characters long with at least 3 of the following: uppercase, lowercase, numbers, or special characters (~!@#$%^*-_=+[{]}/;:,.?).</p><br>
                <br>
                <button type="submit" class="btn btn-default btn-signup" name ="btn-signup" value ="cust-signup">sign up</button>
            </form><br>
        </div>
        <br><br> 
       <footer class="page-footer center-on-small-only" id="footer"></footer>
    </body>
</html>