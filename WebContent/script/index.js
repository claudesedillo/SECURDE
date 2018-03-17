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
			console.log("ajax complete!");
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
});