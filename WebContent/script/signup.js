function validateEmail(email) {
	 var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	 return re.test(email);
}

function redirectCustomer(data){
	if(data == "PASS-SIGNUP-CUSTOMER"){
		document.location.href = 'Index.jsp';
	}
	else{
		document.location.href = 'SignUp.jsp';
	}
}
function signup(email, password, password2){
	var firstname = document.getElementById('fname').value;
	var lastname = document.getElementById('lname').value;
	var	securityQuestion = document.getElementById('security-qs').value;
	var securityAnswer = document.getElementById('security-answer').value;
    
	console.log(email + " " + password + " " + password2 + " " + firstname + " " + lastname + " " + securityQuestion + " " + securityAnswer + " ");
	
    $.ajax({
 	    context: this,
        url:'signupcustomer',
        data:{'email': email,
        	  'fname': firstname,
        	  'lname': lastname,
        	  'password': password,
        	  'password2': password2,
        	  'securityQ': securityQuestion,
        	  'securityA': securityAnswer,
        		},
        type:'POST',
        cache:false,
        success: function(data){
        	console.log("signUp success!");
        	console.log("data is" + data);
        	redirectCustomer(data);
        },
        error:function(){
        	console.log("error at submitting the form");
        }
     });
}

$("document").ready(function() {
	
	$(document).on("click", "#btn-signup",function() {
		console.log("signup clicked!");
		var email = document.getElementById('email-signup').value;
        var password = document.getElementById('password-signup').value;
        var password2 = document.getElementById('confirm-pw').value;
        
        console.log(password + " " + password2);
        
        if(validateEmail(email) && password !== null && password !== "" && password == password2){
        	signup(email, password, password2); 
        } else{
        	console.log("Something's wrong");
        }
	});
	
	var strength = {
	  0: "Worst",
	  1: "Bad",
	  2: "Weak",
	  3: "Good",
	  4: "Strong"
	}
	var password = document.getElementById('password-signup');
	var meter = document.getElementById('password-strength-meter');

	password.addEventListener('input', function() {
	  var val = password.value;
	  var result = zxcvbn(val);
	
	  // Update the password strength meter
	  meter.value = result.score;
	});
})