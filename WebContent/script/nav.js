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
	console.log("im in")
});