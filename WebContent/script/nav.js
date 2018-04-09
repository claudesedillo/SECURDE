function getCartCount(){
	
	var badge = document.getElementById("badge");
	var badgeText = document.getElementById("badge").innerHTML;
	$.ajax({
		context: this,
		url: 'getCartCount',
		type: 'get',
		cache: false,
		success: function(data){
			console.log("badge content: " + badgeText);
			console.log("Cart count: " + data);
			$(badge).text("WOOOOOOOOO");
			console.log("badge content: " + badgeText);
			console.log("append complete!");
		},
		error:function(){
			console.log("something is wrong on getCartCount");
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

function accPassMismatch(data){
	console.log("data is: " + data);
	if(data == "PASS-LOGIN-CUSTOMER"){
		document.location.href = 'Index.jsp';
	}
	else if(data == "FAIL-LOGIN-CUSTOMER"){
		console.log("wrong password");
		showError();
	}
}

function submitTheForm(email, password){
    $.ajax({
 	    context: this,
        url:'login',
        data:{'email': email,
        	  'password': password},
        type:'POST',
        cache:false,
        success: function(data){
        	console.log("submitTheForm success!");
        	accPassMismatch(data);
        },
        error:function(){
        	console.log("error at submitting the form");
        }
     });
}

$("document").ready(function(){
	
	if(document.cookie.indexOf("logged") >= 0){   	 	
			$("#nav").load("usernav.html");
		    $("#footer").load("footer.html");
	    	console.log("Log in successful");
	    	console.log("Nav and footer loaded!");
    }else {
			$("#nav").load("nav.html");
		    $("#footer").load("footer.html");
		    console.log("Nav and footer loaded!");
    }
	 
	$(document).on("click", "#btn-cart", function(){
	  	console.log("Cart was clicked");
	   	var bookID = $(this).attr("data-bookId");
	   	console.log("BookID =  " + bookID);
	   	window.location = "intoCart";  
	});
	
	$(document).on("click", ".close", function() {
		$("#errormsg").hide();
		$("#signin-modal .form-group").removeClass("has-error");
		$("#signin-modal form")[0].reset();
	})

	// submit form for button
	$(document).on("click", "#btn-signin",function() {
		console.log("Sign in clicked");
		
		var email = document.getElementById('email').value;
        var password = document.getElementById('password').value;
        
        if(validateEmail(email) && password !== null && password !== ""){
        	$("#signin-modal .form-group").removeClass("has-error");
        	submitTheForm(email, password); 
        } else{
        	showError();
        }
	});
	
//	$(document).keyup(function (e) {
//		var email = document.getElementById('email').value;
//        var password = document.getElementById('password').value;
        
//        if(validateEmail(email) && password !== null && password !== ""){
//        	$(".form-group").removeClass("has-error");
//        	submitTheForm(email, password); 
//        } else{
//        	showError();
//        }
//    });

	console.log("Nav.js loaded");
});