function validateEmail(email) {
	 var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	 return re.test(email);
}

function redirectUser(data){
	if(data == "PROCEED-DOOR"){
		document.location.href = 'ResetPasswordDoor.jsp';
	}
	else{
		$(".warnings").show();
	}
}

function submitTheForm(email){
    $.ajax({
 	    context: this,
        url:'forgotPassword',
        data:{'email': email
        	},
        type:'post',
        cache:false,
        success: function(data){
        	console.log("data is: " + data);
        	redirectUser(data);
        },
        error:function(){
        	console.log("error at submitting the form");
        }
     });
}
$("document").ready(function(){

    $(document).on("click", "#btn-submit", function(){
    	
    	var email2 = document.getElementById('email').value;
    	
    	if (validateEmail(email2)) {
    		submitTheForm(email2);
    	} else $('.warnings').show();
    });
});