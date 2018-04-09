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
			console.log("displayCatalog ajax complete!");
		},
		error:function(){
			console.log("something is wrong on displayCatalog");
		}
	});
}

$("document").ready(function(){
	
    // view book
    $(document).on("click", ".bookLink", function(){
    	console.log("book link was clicked!");
    	var bookID = $(this).attr("data-bookId");
    	console.log("book id is " + bookID);
    	window.location = "viewBook?bookID=" + bookID;  
    });
    
	displayCatalog();
    
    // submit for on enter
//    $(document).keyup(function (e) {
//		var email = document.getElementById('email').value;
//        var password = document.getElementById('password').value;
//        
//        if(validateEmail(email) && password !== null && password !== ""){
//        	$(".form-group").removeClass("has-error");
//        	submitTheForm(email, password); 
//        } else{
//        	showError();
//        }
//    });
});