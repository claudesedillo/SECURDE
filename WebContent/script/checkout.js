function validateEmail(email) {
	 var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	 return re.test(email);
}

function checkNumInput(input) {
	return /^\d+$/.test(input)
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

function checkoutConfirm(fname, lname, postalcode, streetaddress, city, province, 
		phonenumber, postalcode){
	console.log("firstname is" + firstname);
	console.log("lastname is" + lastname);
	console.log("postal code is " + postalcode);
	console.log("streetaddress is " + streetaddress);
	console.log("city is" + city);
	console.log("province is" + province);
	console.log("phonenumber is" + phonenumber);
	console.log("postalcode is" + postalcode);
	
    $.ajax({
 	    context: this,
        url:'checkoutConfirm',
        data:{'firstname': firstname,
        	'lastname':lastname,
        	'streetaddress':streetaddress,
        	'city':city,
        	'province':province,
        	'phonenumber':phonenumber,
        	'postalcode':postalcode},
        type:'POST',
        cache:false,
        success: function(data){
        	if(data == "SUCCESS-CHECKOUT")
        		document.location.href = 'Index.jsp';
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
	
	$(document).on("click", "#btn-checkoutConfirm",function() {
		var firstname = $('#fname-inp').val();
		var lastname = $('#lname-inp').val();
		var streetaddress = $('#address-inp').val();
		var city = $('#city-inp').val();
		var province = $('#province-input').val();
		var phonenumber =  $('#phonenumber-inp').val();
		var postalcode = $('#postalcode-inp').val();
		
		$('.warning').hide();
		
		console.log("Name: "+ firstname);
		
		if (firstname &&
				lastname &&
				streetaddress &&
				city &&
				province &&
				checkNumInput (phonenumber) &&
				checkNumInput (postalcode)) {
			console.log("Checkoutonfirm clicked AT FUCKING CHECKOUT.JS!");
			checkoutConfirm(firstname, lastname, postalcode, streetaddress, 
					city, province, phonenumber, postalcode);
		} else {
			if (firstname == " " || firstname == null)
				$("#fname-error").show();
			if (lastname == " ")
				$("#lname-error").show();
			if (streetaddress == " ")
				$("#staddress-error").show();
			if (city == " ")
				$("#city-error").show();
			if (province == " ")
				$("#province-error").show();
			if (phonenumber == " " || !(checkNumInput(phonenumber)))
				$("#phone-error").show();
			if (postalcode == " " || !(checkNumInput(postalcode)))
				$("#postal-error").show();
			
		}
		
	});
	
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