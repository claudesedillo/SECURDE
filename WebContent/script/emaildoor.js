/**
 *  TODO parse inputs to string, append, then compare to actual code
 */

$("document").ready(function(){
    var code;
    
    $('.code-fields').keyup(function () {
        if (this.value.length == this.maxLength) {
          $(this).next('.code-fields').focus();
        }
    });
})
