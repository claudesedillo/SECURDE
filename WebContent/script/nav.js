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
	 
	$(document).on("click", "#btn-cart", function(){
	  	console.log("cart was clicked!");
	   	var bookID = $(this).attr("data-bookId");
	   	console.log("book id is " + bookID);
	   	window.location = "intoCart";  
	});
	
	console.log("im in nav.js")
});