function getPrice(){
	
	$.ajax({
		context: this,
		url: 'getPrice',
		type: 'get',
		cache: false,
		success: function(data){
			$("#totalprice").append(data);
			console.log("ajax complete!");
		},
		error:function(){
			console.log("something is wrong on getOrder");
		}
	});
}

$("document").ready(function(){
	//console.log("Console ready on checkout!");
	//getPrice();
});