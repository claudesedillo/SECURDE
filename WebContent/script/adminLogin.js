function validateEmail(email) {
	 var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	 return re.test(email);
}

function accPassMismatch(data){
	console.log("data is: " + data);
	if(data == "PASS-LOGIN-ADMIN"){
		document.location.href = 'EmailDoor.jsp';
	}
	else if(data == "FAIL-LOGIN-ADMIN"){
		console.log("wrong password");
		showError();
	}
}

function passToServlet(email, password){
	$.ajax({
 	    context: this,
        url:'adminLogin',
        data:{'email': email,
        	  'password': password},
        type:'POST',
        cache:false,
        success: function(data){
        	console.log("passToServlet success!");
        	console.log("data is " + data);
        	accPassMismatch(data)
        },
        error:function(){
        	console.log("error at submitting the form");
        }
     });
}

$("document").ready(function() {
	$(document).on("click", "#btn-signin",function() {
		console.log("signin clicked!");
		
		var email = document.getElementById('email-admin').value;
        var password = document.getElementById('password-admin').value;
        
        if(validateEmail(email)){
        	passToServlet(email, password); 
        } else{
        	console.log("Something's wrong");
        }
	});
});