var genre;    
$(document).on("click", ".searchButton", function(){
	console.log("search button was clicked using documentOnClick!");
	var searchTerm = $("#searchBox").val();
	var temp = searchTerm;
	if(searchTerm.length == 0 || $.trim(temp) == '' )
		alert("Please enter an input");
    else
    	window.location = "search?searchTerm=" + searchTerm;
});
    
$(document).on("click", ".browseByGenre", function(){
  	genre = $(this).attr("data-genre");
   	console.log("Genre is " + genre);
   	window.location = "viewBook?bookID=" + genre;  
});