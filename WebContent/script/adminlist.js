function displayAdmin(){
	console.log("Hello!");
	
	var catalog = document.getElementById("adminDiv");
	
	$.ajax({
		context: this,
		url: 'getAdminList',
		type: 'post',
		cache: false,
		success: function(data){
			$(catalog).append(data);
			console.log(data);
			console.log("ajax complete!");
		},
		error:function(){
			console.log("something is wrong on displayCompleteCatalog");
		}
	});
}

$("document").ready(function() {
	displayAdmin();
});