var accountDetails;
var namef, streetaddress, cityf, provincef, postalcodef, phonef;
var name, email, address, phone;

function checkNumInput(input) {
	return /^\d+$/.test(input)
}


function getAccountDetails(){
	$.ajax({
		context: this,
		url: 'getAccountDetails',
		type: 'get',
		cache: false,
		success: function(data){
			accountDetails = data;
			updateProfileFields();
		},
		error:function(){
			console.log("Error encountered at getAccountDetails");
		}
	});
}

function updateProfileFields(name, email, address, phone){
	name = accountDetails.firstname + " " + accountDetails.lastname;
	email = accountDetails.email;
	address = accountDetails.streetaddress + " " + accountDetails.postalcode + " " + accountDetails.city + " " + accountDetails.province;
	phone = accountDetails.phonenumber;
	
	$("#name").val(name);
	$("#email").val(email);
	
	if(accountDetails.streetaddress == undefined)
		$("#comb-address").val("Not specified");
	else
		$("#comb-address").val(address);
	
	if(phone != undefined)
		$("#phone").val(phone);
	else
		$("#phone").val("");
}

function updateAccountDetails(){
	$.ajax({
		context: this,
		url: 'updateAccountDetails',
		data:{'email' : accountDetails.email,
			  'name' : $("#name").val(),
			  'streetAddress' : $("#st-address").val(),
			  'city' : $("#city").val(),
			  'province' : $("#province").val(),
			  'postalcode': $("#postalcode").val(),
			  'phone': $("#phone").val()
			  },
		type: 'post',
		cache: false,
		success: function(data){
			console.log("Account Details successfully updated!");
			console.log(data);
		},
		error:function(){
			console.log("Error encountered at updateAccountDetails.");
		}
	});	
}

function displayOrders(){
		
	var orderTable = document.getElementById("orderdetail-ordertable");
		
	$.ajax({
		context: this,
		url: 'getUserOrders',
		type: 'get',
		cache: false,
		success: function(data){
			$(orderTable).append(data);
			console.log("displayOrders complete!");
		},
		error:function(){
			console.log("something is wrong on displayOrders");
		}
	});
}

function getOrderDetails(orderID){
	var orderDetailsTable = document.getElementById("orderdetails-ordertable");
	
	$.ajax({
		context: this,
		url: 'getOrderDetails',
		data:{'orderID' : orderID},
		type: 'get',
		cache: false,
		success: function(orderDetails){
			console.log("getOrderDetails complete!");
			console.log(orderDetails);
			$(orderDetailsTable).append(orderDetails);
		},
		error:function(){
			console.log("something is wrong on getBookDetails");
		}
	});
}

function getOrderSummary(orderID){
	var orderSummaryDiv = document.getElementById("ordersummary-div");
	
	$.ajax({
		context: this,
		url: 'getOrderSummary',
		data:{'orderID' : orderID},
		type: 'get',
		cache: false,
		success: function(orderSummary){
			console.log("getOrderSummary complete!");
			console.log(orderSummary);
			$(orderSummaryDiv).append(orderSummary);
		},
		error:function(){
			console.log("something is wrong on getOrderSummary");
		}
	});	
}

function appendAddress () {
	document.getElementById('comb-address').value = 
		document.getElementById('st-address').value + ' ' +
		document.getElementById('city').value + ' ' +
		document.getElementById('province').value + ' ' + 
		document.getElementById('postalcode').value;
	console.log("Address appended");
}

$(document).ready(function() { 
	displayOrders();
	getAccountDetails();
	
	namef = document.getElementById("name").value;
	streetaddressf = document.getElementById("st-address").value;
	cityf = document.getElementById("city").value;
	provincef  = document.getElementById("province").value;
	postalcodef = document.getElementById("postalcode").value;
	phonef = document.getElementById("phone").value;
	
	console.log("namef" + namef);
	console.log("streedaddressf" + streetaddressf);
	console.log("cityf" + cityf);
	console.log("provincef" + provincef);
	console.log("postalcodef" + postalcodef);
	console.log("phonef" + phonef);
	
	$(document).on("click", ".view-orderdetails-btn", function(){
		console.log("view order details clicked!");
		
		var orderID = $(this).attr("data-orderid");
		console.log("order ID: " + orderID);
		getOrderSummary(orderID);
		getOrderDetails(orderID);
	});
	
	$("#details-modal").on("hidden.bs.modal", function () {
		$("#ordersummary-div").empty();
		$("#orderdetails-ordertable").empty();
	    console.log("modal closed!");
	});
	
	// edit account details
    $('#btn-editacc').click(function() {
        $('form#form-editacc input.editacc-fields').removeAttr('disabled');
        $('.combaddress-div').hide();
        $('.address-div').slideDown();
        $('form#form-editacc input.address-fields').removeAttr('disabled');
        $('#btn-editacc').hide();
        $('#save-editacc').show();
        
    })
    
    // save account details
    $('#save-editacc').click(function() {
    	console.log("Name: "+ namef);
    	if (namef &&
			streetaddressf &&
			cityf &&
			provincef &&
			checkNumInput(postalcodef) &&
			checkNumInput(phonef)) {
    		console.log("Name: "+ namef);
    	} else {
    		if (namef == "")
    			$('#name-error').show();
    		if (streetaddressf == "")
    			$('#st-error').show();
    		if (cityf == "")
    			$('#city-error').show();
    		if (provincef == "")
    			$('#prov-error').show();
    		if (!(checkNumInput(postalcodef)) || postalcodef == "")
    			$('#postal-error').show();
    		if (!(checkNumInput(phonef)) || phonef == "")
    			$('#mobile-error').show();
    		
    		
    	}
    		
    		
    	/**$('form#form-editacc input.editacc-fields').attr('disabled', 'disabled');
        $('.combaddress-div').slideDown();
        $('.address-div').slideUp();
        $('form#form-editacc input.address-fields').attr('disabled', 'disabled');
        $('#btn-editacc').show();
        $('#save-editacc').hide();
        appendAddress();
        updateAccountDetails(); **/
    })
    
    // change password
    $('#btn-changepw').click(function() {
    	$('#oldpw').val("");
        $('.newpw-div').slideDown();
        $('form#form-changepw input.changepw-fields').removeAttr('disabled');
        $('.help-text').slideDown();
        $('#btn-changepw').hide();
        $('#save-changepw').show();
    })
    
    // save password change
    $('#save-changepw').click(function() {
        $('.newpw-div').slideUp();
        $('form#form-changepw input.changepw-fields').attr('disabled', 'disabled');
        $('.help-text').slideUp();
        $('#btn-changepw').show();
        $('#save-changepw').hide();
    })
    
    var strength = {
  	  0: "Worst",
  	  1: "Bad",
  	  2: "Weak",
  	  3: "Good",
  	  4: "Strong"
  	}
  	var password = document.getElementById('newpw');
  	var meter = document.getElementById('password-strength-meter');

  	password.addEventListener('input', function() {
  	  var val = password.value;
  	  var result = zxcvbn(val);
  	
  	  // Update the password strength meter
  	  meter.value = result.score;
  	});

});