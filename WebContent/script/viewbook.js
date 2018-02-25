$("document").ready(function(){
    
    $(function(){
    	$("#nav").load("nav.html");
        $("#footer").load("footer.html");
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