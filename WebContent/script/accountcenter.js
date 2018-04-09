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
    	$('form#form-editacc input.editacc-fields').attr('disabled', 'disabled');
        $('.combaddress-div').slideDown();
        $('.address-div').slideUp();
        $('form#form-editacc input.address-fields').attr('disabled', 'disabled');
        $('#btn-editacc').show();
        $('#save-editacc').hide();
        appendAddress();
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