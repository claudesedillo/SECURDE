function getOrder(orderID, parent){
	
	$.ajax({
		context: this,
		url: 'getOrderDetails',
		type: 'get',
		data:{'orderID': orderID},
		cache: false,
		success: function(data){
			//console.log(parent);
			$(parent).append(data);
			//console.log("ajax complete!");
		},
		error:function(){
			//console.log("something is wrong on getOrder");
		}
	});
	
	//console.log("order ID is " + orderID);
}

function displayOrders(){
	//console.log("Hello!");
	
	var orders = document.getElementById("orders");
	
	$.ajax({
		context: this,
		url: 'getOrders',
		type: 'get',
		cache: false,
		success: function(data){
			$(orders).append(data);
			//console.log(data);
			//console.log("ajax complete!");
		},
		error:function(){
			//console.log("something is wrong on displayOrders");
		}
	});
}

$("document").ready(function() {
	displayOrders();
	
	$(document).on("click", ".viewOrderButton", function(){
		var orderID = $(this).attr('id');
		var parent = this;
		getOrder(orderID, parent);
	});
});