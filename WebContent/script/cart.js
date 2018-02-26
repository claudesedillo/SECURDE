function displayCart(){
	console.log("Inside Cart script");
	
	var listDiv = document.getElementById("list-div");
	
	$.ajax({
		context: this,
		url : 'getCartList',
		type: 'get',
		cache: false,
		success : function(data){
			$(feed).append(data);
			console.log(data);
			console.log("ajax complete!");
		},
		error:function(){
			console.log("something is wrong with displayCart");
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
    
    displayCart();
});