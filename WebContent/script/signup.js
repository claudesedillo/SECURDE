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

function confirmPasswordMatch(pass1, pass2) {
	var satisfied = true;
	
	if(pass1 != pass2) {
		$('#pwnotmatch').show();
		console.log("NO");
		satisfied = false;
	} else {
		$('#pwnotmatch').hide();
		console.log("YES");
	}	
	
	return satisfied;
}

function checkPassword(value) { 
	// Java special char // ["'\]
	return value.length >= 8 && /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~]).*$/.test(value);
	//#?!@$%^&*-
}

function removeWhiteSpaces(value) {
	return value.replace(/\s+/g,' ').trim();
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
		var fname = document.getElementById('fname').value;
		var lname = document.getElementById('lname').value;
		var email = document.getElementById('email-signup').value;
        var password = document.getElementById('password-signup').value;
        var password2 = document.getElementById('confirm-pw').value;
        var select = document.getElementById('security-qs');
        var securityq = select.options[select.selectedIndex].value;
        var securitya = document.getElementById('security-answer').value;
        
        fname = removeWhiteSpaces(fname);
        lname = removeWhiteSpaces(lname);
        email = removeWhiteSpaces(email);
        securitya = removeWhiteSpaces(securitya);

        $(".warnings").hide();
        console.log("Select is " + securityq);
        
      //  if (confirmPasswordMatch(password, password2))
       // 	console.log("IF-1 true")
        if (validateEmail(email) && 
        		fname && 
        		lname && 
        		securityq &&
        		securitya &&
        		confirmPasswordMatch(password, password2)) {
        	console.log("Successful data validation!");
    		signup(email, password, password2);	
        } 
    	else {
	    	if (!(validateEmail(email)) || email == "" || email == null)
	    		$("#email-error").show();
	    	if (fname == "")
	    		$('#fname-error').show();
	    	if (lname == "")
	    		$('#lname-error').show();
	    	if (!(checkPassword(password)) || password == "")
	    		$('#pw-error').show();
	    	if (securityq  == "security question")
	    		$('#sq-error').show();
	    	if (securitya == "")
	    		$('#sa-error').show();
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