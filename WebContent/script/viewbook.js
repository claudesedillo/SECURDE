function refreshPage(data){
	if(data == "PASS-ADD-CART"){
		location.reload();
	}
	else{
		alert("Error in adding to cart");
	}
}

function addToCart(bookID){
	//console.log("at addtocart. bookid is " + bookID);
	
    $.ajax({
 	    context: this,
        url:'addToCart',
        data:{'bookID': bookID},
        type:'POST',
        cache:false,
        success: function(data){
        	//console.log("addToCart success!!");
        	refreshPage(data);
        },
        error:function(){
        	//console.log("error at addToCart");
        }
     });
}

$("document").ready(function(){
    
	$(document).on("click", "#btn-addtocart", function(){
		var bookID = document.getElementById('btn-addtocart').value;
		//console.log(bookID);
		addToCart(bookID);
		
	});
	
    (function ($) {
        $('.spinner .btn:first-of-type').on('click', function() {
          $('.spinner input').val( parseInt($('.spinner input').val(), 10) + 1);
        });
        $('.spinner .btn:last-of-type').on('click', function() {
          $('.spinner input').val( parseInt($('.spinner input').val(), 10) - 1);
        });
     })(jQuery);
    
});