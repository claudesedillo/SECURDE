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
        <script src="script/resetpwdoor.js"></script>
        
        <title>Bookshelf | Password Recovery</title>
	</head>
	<body>
		<div class="container-fluid">
            <div class="row">
                <a class="title" href="portal.html">bookshelf</a><p>| portal</p>
            </div>
            <hr>
            <div class="row">
                <p>Enter session key</p>
                <form>
                    <input type="password" onfocus="this.value=''" class="form-control code-fields" id = "s1" maxlength="1">
                    <input type="password" onfocus="this.value=''" class="form-control code-fields" id = "s2" maxlength="1">
                    <input type="password" onfocus="this.value=''" class="form-control code-fields" id = "s3" maxlength="1">
                    <input type="password" onfocus="this.value=''" class="form-control code-fields" id = "s4" maxlength="1">
                    <input type="password" onfocus="this.value=''" class="form-control code-fields" id = "s5" maxlength="1"><br><br>
                    <p class="warnings" id="key-error">Invalid key.<br><br></p>
                 </form>
                    <button class="btn btn-default" id ="btn-proceed">proceed</button><br>
                <br><br>
            </div>
        </div>

	</body>
</html>