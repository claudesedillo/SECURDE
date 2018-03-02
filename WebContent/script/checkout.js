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

$("document").ready(function(){
	if(!(document.cookie.indexOf("logged") >= 0)){   	 	
		getCheckoutSignIn();
    }
	getCheckoutDelivery();
	//console.log("Console ready on checkout!");
	//getPrice();
});