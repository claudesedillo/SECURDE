function getCartCount(){
	
	var badge = document.getElementById("badge");
	var badgeText = document.getElementById("badge").innerHTML;
	$.ajax({
		context: this,
		url: 'getCartCount',
		type: 'get',
		async: false,
		cache: false,
		success: function(data){
			//console.log("badge content: " + badgeText);
			//console.log("Cart count: " + data);
			$(badge).text(data);
			//console.log("badge content: " + badgeText);
			//console.log("append complete!");
		},
		error:function(){
			//console.log("something is wrong on getCartCount");
		}
	});
}

function validateEmail(email) {
	 var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	 return re.test(email);
}

function showError() {
    $("#errormsg").show();
    $("#signin-modal .form-group").addClass("has-error");
}

function removeError() {
	$("#errormsg").hide();
	$("#signin-modal .form-group").removeClass("has-error");
}

function accPassMismatch(data){
	//console.log("Data is: " + data);
	if(data == "PASS-LOGIN-CUSTOMER"){
		document.location.href = 'Index.jsp';
	}
	else if(data == "FAIL-LOGIN-CUSTOMER"){
		showError();
	}
}

function toServlet(email, password){
    $.ajax({
 	    context: this,
        url:'login',
        data:{'email': email,
        	  'password': password},
        type:'POST',
        cache:false,
        success: function(data){
        	//console.log("toServletsuccess!");
        	accPassMismatch(data);
        },
        error:function(){
        	//console.log("Error encountered at toServlet");
        }
     });
}

$("document").ready(function(){
	
	if(document.cookie.indexOf("USER") >= 0){   	 	
			$("#nav").load("usernav.html");
		    $("#footer").load("footer.html");
	    	//console.log("Log in successful");
	    	//console.log("Nav and footer (user) loaded!");
    }else {
			$("#nav").load("nav.html");
		    $("#footer").load("footer.html");
		    //console.log("Nav and footer (not logged in) loaded!");
    }
	 
	$(document).on("click", "#btn-cart", function(){
	  	//console.log("Cart was clicked");
	  	document.location.href = 'Cart.jsp';
	});
	
	$(document).on("click", ".close", function() {
		$("#errormsg").hide();
		$("#signin-modal .form-group").removeClass("has-error");
		$("#signin-modal form")[0].reset();
	})

	// submit form for button
	$(document).on("click", "#btn-signin",function() {

		var email = document.getElementById('email').value;
        var password = document.getElementById('password').value;
        
        if(validateEmail(email) && password !== null && password !== ""){
        	removeError();
        	toServlet(email, password); 
        } else showError();
	});
	
	//console.log("Nav.js loaded");
});