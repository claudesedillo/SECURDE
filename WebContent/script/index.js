function displayCatalog(){
	console.log("Hello!");
	
	var catalog = document.getElementById("feed");
	
	$.ajax({
		context: this,
		url: 'getCatalog',
		type: 'get',
		cache: false,
		success: function(data){
			$(feed).append(data);
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
    	$("#nav").load("usernav.html");
    	
    /**	var ls = document.createElement('link');
    	ls.rel="stylesheet";
    	ls.href="css/usernav.css";
    	document.getElementsByTagName('head')[0].appendChild(ls); **/

    }
});