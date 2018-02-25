function displayOrders(){
	console.log("Hello!");
	
	var orders = document.getElementById("orders");
	
	$.ajax({
		context: this,
		url: 'getOrders',
		type: 'get',
		cache: false,
		success: function(data){
			$(orders).append(data);
			console.log(data);
			console.log("ajax complete!");
		},
		error:function(){
			console.log("something is wrong on displayOrders");
		}
	});
}

$("document").ready(function() {
	displayOrders();
});