$("document").ready(function(){
	
    $('.code-fields').keyup(function () {
        if (this.value.length == this.maxLength) {
          $(this).next('.code-fields').focus();
        }
    });
   
})

$(window).bind("pageshow", function() {
        var form = $('form'); 
        // let the browser natively reset defaults
        form[0].reset();
});
