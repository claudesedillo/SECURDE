function displayCatalog(){
	//console.log("Hello!");
	
	var catalog = document.getElementById("catalogDiv");
	
	$.ajax({
		context: this,
		url: 'getCompleteCatalog',
		type: 'get',
		cache: false,
		success: function(data){
			$(catalog).append(data);
			//console.log(data);
			//console.log("ajax complete!");
		},
		error:function(){
			//console.log("something is wrong on displayCompleteCatalog");
		}
	});
}

$("document").ready(function() {
	displayCatalog();
});