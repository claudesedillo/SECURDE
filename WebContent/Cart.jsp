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
        
        <link rel="stylesheet" href="css/cart.css">
        <link rel="stylesheet" href="css/nav.css">
        <link rel="stylesheet" href="css/footer.css">
        <script src="script/nav.js"></script>
</head>
<body>
 		<nav class="navbar navbar-default" id="nav"></nav>
        
        <div class="container-fluid" id="feed">
            <div class="col-sm-8" id="list-div">
                <h5>YOUR SHOPPING CART (2) ₱840.99</h5>
                
                <div class="row book-div">
                    <div class="col-sm-3">
                        <img src="css/generic-cover.jpg" class="img-responsive">
                    </div>
                    <div class="col-sm-9">
                        <button type="button" class="btn btn-delete"><span class="glyphicon glyphicon-remove"></span></button>
                        <p class="title">NO TITLE</p>
                        <p> by <span class="author">André Aciman</span></p>
                        <p class="format">Paperback</p>
                        <p class="price">P420.00</p>
                        
                        <!-- Please check backend implementation - has limits eh... thanks!! https://www.codeply.com/go/2VmBU7TanF/bootstrap-plus-minus-counter-input -->
                        <div class="col-sm-3">
                            <div class="input-group">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-default btn-number" disabled="disabled" data-type="minus" data-field="quant[1]"><span class="glyphicon glyphicon-minus"></span></button>
                                </span>
                                <input type="text" name="quant[1]" class="form-control input-number" value="1" min="1" max="10">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-default btn-number" data-type="plus" data-field="quant[1]"><span class="glyphicon glyphicon-plus"></span>
                                </button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <hr>
                </div>
                
               <div class="row book-div">
                    <div class="col-sm-3">
                        <img src="css/generic-cover.jpg" class="img-responsive">
                    </div>
                    <div class="col-sm-9">
                        <button type="button" class="btn btn-delete"><span class="glyphicon glyphicon-remove"></span></button>
                        
                        <p class="title">NO TITLE</p>
                        <p> by <span class="author">André Aciman</span></p>
                        <p class="format">Paperback</p>
                        <p class="price">P420.00</p>
                        
                        
                        <!-- Please check backend implementation - has limits eh... thanks!! https://www.codeply.com/go/2VmBU7TanF/bootstrap-plus-minus-counter-input -->
                        <div class="col-sm-3">
                            <div class="input-group">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-default btn-number" disabled="disabled" data-type="minus" data-field="quant[1]"><span class="glyphicon glyphicon-minus"></span></button>
                                </span>
                                <input type="text" name="quant[1]" class="form-control input-number" value="1" min="1" max="10">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-default btn-number" data-type="plus" data-field="quant[1]"><span class="glyphicon glyphicon-plus"></span>
                                </button>
                                </span>
                            </div>
                        </div>
                          
                    </div>
                </div>
            </div>
            
            <div class="col-sm-4" id="total-div">
                <div class="col-sm-5">
                    <p>SUBTOTAL: </p>
                </div>
                
                <div class="col-sm-7">
                    <p id="totalprice">P840.00</p>
                </div>
                
                <button type="button" class="btn btn-default" id="btn-checkout">CHECKOUT</button>
            </div>
        </div>
        <br><br>
        <footer class="page-footer center-on-small-only" id="footer"></footer>
</body>
</html>