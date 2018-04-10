function redirectUser(data){
	if(data == "PROCEED-DOOR"){
		document.location.href = 'ResetPasswordDoor.jsp';
	}
	else{
		console.log("Wrong key!");
	}
}

function submitTheForm(){
	
	var email = document.getElementById('email').value;
	console.log("email is " + email);
	
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
    	console.log("clicked on submit");
    	submitTheForm();
    });
});