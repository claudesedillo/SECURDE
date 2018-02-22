$("document").ready(function(){
    
    $(function(){
    	$("#nav").load("nav.html");
        $("#footer").load("footer.html");
    });
    
    if(document.cookie.indexOf("logged") >= 0){
    	console.log("congrats you logged in )
    }
});