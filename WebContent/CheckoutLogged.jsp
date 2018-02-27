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
        
        <link rel="stylesheet" href="css/checkout.css">
        <link rel="stylesheet" href="css/footer.css">
<title>Insert title here</title>
</head>
<body>
 <nav class="navbar navbar-default">
            <div class="container-fluid main-nav">
                <div class="wrapper">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="index.html">bookshelf</a>
                    </div>
                    <p class="navbar-text">SECURE CHECKOUT</p>
                </div>
            </div>
        </nav>
                
        <div class="container-fluid" id="feed">
            <div class="col-sm-8">
                <div id="accordion">
                    
                    <!-- SIGN IN -->
                   <!--  <div class="card">
                        <div class="card-header" id="ch-signin">
                          <h5 class="mb-0"><button class="btn btn-link" data-toggle="collapse" data-target="#signin-card" aria-expanded="true" aria-controls="signin-card">SIGN IN</button></h5>
                        </div>

                        <div id="signin-card" class="collapse show" aria-labelledby="ch-signin" data-parent="#accordion">
                            <div class="card-body">
                                <div class="row">
                                    <!-- <div class="col-sm-6" id="signin-div">
                                        <p>Sign in to your Bookshelf account</p>
                                        <a href="#">want to create an online account?</a><br><br>
                                        <form>
                                            <div class="form-group">
                                                <input type="email" class="form-control" id="email" placeholder="email address">
                                            </div>
                                             <div class="form-group">
                                                <input type="password" class="form-control" id="password" placeholder="password">
                                            </div>

                                            <button type="button" class="btn btn-default" id="btn-signin">sign in</button>
                                            <a href="#">forgot password?</a>
                                        </form>
                                    </div> 

                                    <div class="col-sm-6" id="guestco-div">
                                        <p>Checkout as a Guest</p>
                                        <p class="sub">your email will be used to confirm your order.</p>
                                        <form>
                                            <div class="form-group">
                                                <input type="email" class="form-control" id="email" placeholder="email address">
                                            </div>

                                            <button type="button" class="btn btn-default" id="btn-next1" >next</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>  -->
                    
                    <!-- DELIVERY -->
                    <div class="card">
                        <div class="card-header" id="ch-delivery">
                            <h5 class="mb-0"><button class="btn btn-link collapsed" data-toggle="collapse" data-target="#delivery-card" aria-expanded="false" aria-controls="delivery-card">DELIVERY</button></h5>
                        </div>
                        
                        <div id="delivery-card" class="collapse" aria-labelledby="ch-delivery" data-parent="#accordion">
                            <div class="card-body">
                                <div class="row">
                                     <form class="form-horizontal" action = "checkoutConfirm" method = "POST" >
                                         <div class="form-group">
                                             <label class="control-label col-sm-2" for="fname-inp">First Name</label>
                                             <div class="col-sm-10">
                                                 <input type="text" class="form-control" id="fname-inp" name = "firstname">
                                             </div>
                                         </div>
                                         <div class="form-group">
                                             <label class="control-label col-sm-2" for="lname-inp">Last Name</label>
                                             <div class="col-sm-10">
                                                 <input type="text" class="form-control" id="lname-inp" name = "lastname">
                                             </div>
                                        </div>
                                        <div class="form-group">
                                             <label class="control-label col-sm-2" for="address-inp">Address</label>
                                             <div class="col-sm-10">
                                                 <input type="text" class="form-control" id="address-inp" name = "address">
                                             </div>
                                        </div>
                                         
                                        <div class="form-group">
                                             <label class="control-label col-sm-2" for="city-inp">City</label>
                                             <div class="col-sm-10">
                                                 <input type="text" class="form-control" id="city-inp" name = "city">
                                             </div>
                                        </div>
                                         
                                        <input type="submit" class="btn btn-default" id="btn-next2" value = "next">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                <!--  <div class="card">
                    <div class="card-header" id="ch-shipping">
                      <h5 class="mb-0">
                        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#shipping-card" aria-expanded="false" aria-controls="shipping-card">
                          SHIPPING
                        </button>
                      </h5>
                    </div>
                    <div id="shipping-card" class="collapse" aria-labelledby="ch-shipping" data-parent="#accordion">
                      <div class="card-body">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                      </div>
                    </div>
                  </div>
                </div>  -->
                </div> 
            </div>
            
            
            <div class="col-sm-4" id="total-div">
                 <div class="col-sm-5">
                    <p>ORDER TOTAL: </p>
                </div>
                
                <div class="col-sm-7">
                    <p id="totalprice">P</p>
                </div>
            </div>
        </div>
        <br><br>
        <footer class="page-footer center-on-small-only" id="footer"></footer>

</body>
</html>