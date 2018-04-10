function displayCart(){
	console.log("Inside displayCart");
	
	var listDiv = document.getElementById("list-div");
	
	$.ajax({
		context: this,
		url : 'getCartList',
		type: 'get',
		cache: false,
		success : function(data){
			console.log("at displayCart. Data is ");
			console.log(data);
			$(listDiv).append(data);
			console.log("getCartajax complete!");
		},
		error:function(){
			console.log("something is wrong with displayCart");
		}
	});
	
}

function displayCheckOut(){
	console.log("Inside displayCheckout");
	
	var totalDiv = document.getElementById("total-div");
	
	$.ajax({
		context: this,
		url : 'checkout',
		type: 'get',
		cache: false,
		success : function(data){
			console.log("at displayCheckOut. Data is ");
			console.log(data);
			$(totalDiv).append(data);
			console.log("displayCheckout ajax complete!");
		},
		error:function(){
			console.log("something is wrong with displayCart");
		}
	});
	
}

$("document").ready(function(){
	
    if(document.cookie.indexOf("USER") >= 0){   	 	
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
    displayCheckOut();
});