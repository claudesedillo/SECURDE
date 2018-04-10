function validateEmail(email) {
	 var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	 return re.test(email);
}

function showError() {
    $("#key-error").show();
    $(".form-group").addClass("has-error");
}

function removeError() {
	$("#key-error").hide();
	//$(".form-group").removeClass("has-error");
}

function accPassMismatch(data){
	console.log("Data is: " + data);
	if(data == "PASS-LOGIN-ADMIN"){
		document.location.href = 'EmailDoor.jsp';
	}
	else if(data == "FAIL-LOGIN-ADMIN"){
		showError();
	}
}

function toServlet(email, password){
	$.ajax({
 	    context: this,
        url:'adminLogin',
        data:{'email': email,
        	  'password': password},
        type:'POST',
        cache:false,
        success: function(data){
        	console.log("toServletsuccess!");
        	accPassMismatch(data);
        },
        error:function(){
        	console.log("Error encountered at toServlet");
        }
     });
}

$("document").ready(function() {
	$(document).on("click", "#btn-signin",function() {
		
		var email = document.getElementById('email-admin').value;
        var password = document.getElementById('password-admin').value;
        
        if(validateEmail(email) && password !== null && password !== ""){
        	removeError();
        	toServlet(email, password); 
        } else showError();
	});
});