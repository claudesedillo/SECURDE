function displayCatalog(){
	console.log("Hello!");
	
	var catalog = document.getElementById("catalog");
	
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

$("document").ready(function(){
    
	displayCatalog();
    $(function(){
    	$("#nav").load("nav.html");
        $("#footer").load("footer.html");
    });
    
    if(document.cookie.indexOf("logged") >= 0){
    	console.log("congrats you logged in");
    }
});