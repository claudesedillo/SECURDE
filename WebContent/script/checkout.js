function validateEmail(email) {
	 var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	 return re.test(email);
}


function getCheckoutSignIn(){
	
	var accordian = document.getElementById("accordion");
	
	$.ajax({
		context: this,
		url: 'getCheckoutSignIn',
		type: 'get',
		cache: false,
		success: function(data){
			$(accordian).append(data);
			console.log("ajax complete!");
		},
		error:function(){
			console.log("something is wrong on getOrder");
		}
	});
}

function getCheckoutDelivery(){
	
	var accordian = document.getElementById("accordion");
	
	$.ajax({
		context: this,
		url: 'getCheckoutDelivery',
		type: 'get',
		cache: false,
		success: function(data){
			$(accordian).append(data);
			console.log("ajax complete!");
		},
		error:function(){
			console.log("something is wrong on getOrder");
		}
	});
}

function getCheckoutPrice(){
	
	var feed = document.getElementById("feed");
	
	$.ajax({
		context: this,
		url: 'getCheckoutPrice',
		type: 'get',
		cache: false,
		success: function(data){
			$(feed).append(data);
			console.log("ajax complete!");
		},
		error:function(){
			console.log("something is wrong on getOrder");
		}
	});
}

function accPassMismatch(data){
	if(data == "PASS-LOGIN-CUSTOMER"){
		document.location.href = 'Checkout.jsp';
	}
	else if(data == "FAIL-LOGIN-CUSTOMER"){
		//put error message here
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
        	console.log("toServletsuccess!");
        	accPassMismatch(data);
        },
        error:function(){
        	console.log("Error encountered at toServlet");
        }
     });
}

function checkoutGuestEmail(email){
    $.ajax({
 	    context: this,
        url:'checkoutGuestEmail',
        data:{'email': email},
        type:'POST',
        cache:false,
        success: function(data){
        	document.location.href = 'Checkout.jsp';
        },
        error:function(){
        	console.log("Error encountered at toServlet");
        }
     });
}
$("document").ready(function(){
	
	if(!(document.cookie.indexOf("USER") >= 0) && !(document.cookie.indexOf("GUEST-CHECKOUT") >= 0)){
		getCheckoutSignIn();
		console.log("user cookie does not exist");
	}
	else{
		getCheckoutDelivery();
		getCheckoutPrice();
	}
//	var verified = false, checkedUser = false;
//	
//	if(!(document.cookie.indexOf("USER") >= 0)){   	 	
//		getCheckoutSignIn();
//		console.log("user cookie does not exist");
//		checkedUser = true;
//    }
//	else{
//		getCheckoutDelivery();
//		getCheckoutPrice();
//		verified = true;
//	}
//	if(!verified && !checkedUser){
//		if(!(document.cookie.indexOf("GUEST-CHECKOUT") >= 0)){
//			getCheckoutSignIn();
//			console.log("checkout cookie does not exist");
//		}
	

	
	$(document).on("click", "#btn-signin",function() {

		var email = document.getElementById('signin-email').value;
        var password = document.getElementById('siginin-password').value;
        
        if(validateEmail(email) && password !== null && password !== ""){
        	toServlet(email, password); 
        }else{
        	//place error message here
        }
	});
	
	$(document).on("click", "#btn-guestemail", function(){
		
		var email = document.getElementById('guest-email').value;
		
        if(validateEmail(email)){
        	checkoutGuestEmail(email); 
        }else{
        	//place error message here
        }
	});


	
});