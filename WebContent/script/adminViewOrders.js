function displayOrders(){
	console.log("Hello!");
		
	var orderTable = document.getElementById("orderTable");
		
	$.ajax({
		context: this,
		url: 'getOrders',
		type: 'get',
		cache: false,
		success: function(data){
			$(orderTable).append(data);
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