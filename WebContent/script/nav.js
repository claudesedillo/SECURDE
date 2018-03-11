function getCartCount(){
	
	var badge = document.getElementById("badge");
	
	$.ajax({
		context: this,
		url: 'getCartCount',
		type: 'get',
		cache: false,
		success: function(data){
			console.log("Cart count: " + data);
			$(badge).text(data);
			console.log("append complete!");
		},
		error:function(){
			console.log("something is wrong on getCartCount");
		}
	});
}

$("document").ready(function(){
	
	 if(document.cookie.indexOf("logged") >= 0){   	 	
			$("#nav").load("usernav.html");
		    $("#footer").load("footer.html");
	    	console.log("Log in successful");
    }else {
			$("#nav").load("nav.html");
		    $("#footer").load("footer.html");
    }
	 
	$(document).on("click", "#btn-cart", function(){
	  	console.log("Cart was clicked");
	   	var bookID = $(this).attr("data-bookId");
	   	console.log("BookID =  " + bookID);
	   	window.location = "intoCart";  
	});
	
	console.log("I'm in nav.js");
});