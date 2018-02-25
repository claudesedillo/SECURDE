function displayCatalog(){
	console.log("Hello!");
	
	var feed = document.getElementById("feed");
	
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
	
    if(document.cookie.indexOf("logged") >= 0){   	 	
		$("#nav").load("usernav.html");
	    $("#footer").load("footer.html");
    	console.log("congrats you logged in");
    }else {
	    $(function(){
			$("#nav").load("nav.html");
		    $("#footer").load("footer.html");
	    });
    }
    
    $(document).on("click", ".bookLink", function(){
    	console.log("book link was clicked!");
    	var bookID = $(this).attr("data-bookId");
    	console.log("book id is " + bookID);
    	window.location = "viewBook?bookID=" + bookID;  
    });
    
	displayCatalog();
});