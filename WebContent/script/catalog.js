function displayCatalog(){
	console.log("Hello!");
	
	var catalog = document.getElementById("catalogDiv");
	
	$.ajax({
		context: this,
		url: 'getCatalog',
		type: 'get',
		cache: false,
		success: function(data){
			$(catalog).append(data);
			console.log(data);
			console.log("ajax complete!");
		},
		error:function(){
			console.log("something is wrong on displayCatalog");
		}
	});
}

$("document").ready(function() {
	
	displayCatalog();
});