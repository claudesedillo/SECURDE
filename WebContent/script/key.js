/**
 *  TODO parse inputs to string, append, then compare to actual code
 */

function redirectAdmin(data){
	if(data == "SUCCESS-LOGIN-ADMIN"){
		document.location.href = 'AdminDashboard.jsp';
	}
	else{
		//error message here
	}
}
function submitTheForm(){
	var s1 = document.getElementById('s1').value;
	var s2 = document.getElementById('s2').value;
	var s3 = document.getElementById('s3').value;
	var s4 = document.getElementById('s4').value;
	var s5 = document.getElementById('s5').value;
	
    $.ajax({
 	    context: this,
        url:'emailKey',
        data:{'s1': s1,
        	's2': s2,
        	's3': s3,
        	's4': s4,
        	's5': s5},
        type:'POST',
        cache:false,
        success: function(data){
        	console.log("submitTheForm success!");
        	console.log("data is: " + data);
        	redirectAdmin(data);
        },
        error:function(){
        	console.log("error at submitting the form");
        }
     });
}

$("document").ready(function(){
    var code;
    
    $(document).on("click", "#btn-signin", function(){
    	console.log("Sign in clicked!");
    	submitTheForm();
    });
    
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
