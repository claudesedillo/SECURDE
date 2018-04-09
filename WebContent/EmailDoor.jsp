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
        <script src="script/emaildoor.js"></script>
        
        <title>Bookshelf | Employee Portal</title>
	</head>
	<body>
		<div class="container-fluid">
            <div class="row">
                <a class="title" href="portal.html">bookshelf</a><p>| portal</p>
            </div>
            <hr>
            <div class="row">
                <p>Enter session key</p>
                <form action = "emailKey" method = "POST">
                    <input type="text" class="form-control code-fields" name = "s1" maxlength="1">
                    <input type="text" class="form-control code-fields" name = "s2" maxlength="1">
                    <input type="text" class="form-control code-fields" name = "s3" maxlength="1">
                    <input type="text" class="form-control code-fields" name = "s4" maxlength="1">
                    <input type="text" class="form-control code-fields" name = "s5" maxlength="1">
                    <Button type="submit" name ="btn-signup" value ="admin-signup">Confirm</Button><br>
                </form>
                <br><br>
            </div>
        </div>

	</body>
</html>