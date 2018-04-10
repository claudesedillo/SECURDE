/**
 *  TODO parse inputs to string, append, then compare to actual code
 */
function redirectUser(data){
	if(data == "SUCCESS-RECOVER-PASS"){
		document.location.href = 'Index.jsp';
	}
	else{
		console.log(data);
		$('.warnings').show();
	}
}

function confirmPasswordMatch(pass1, pass2) {
	var satisfied = true;
	
	if(pass1 != pass2) {
		$('.warnings').show();
		console.log("NO");
		satisfied = false;
	} else {
		$('.warnings').hide();
		console.log("YES");
	}	
	
	return satisfied;
}

function submitTheForm(pass, pass2){
    $.ajax({
 	    context: this,
        url:'newPasswordConfirm',
        data:{'pass': pass,
        	  'pass2': pass2
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
    	var pass = document.getElementById('pass').value;
    	var pass2 = document.getElementById('pass2').value;
    	
    	if (confirmPasswordMatch(pass,pass2) && pass != "") {
    		submitTheForm(pass, pass2);
    	} else $('.warnings').show();

   });
    
	var strength = {
	  0: "Worst",
	  1: "Bad",
	  2: "Weak",
	  3: "Good",
	  4: "Strong"
	}
	var password = document.getElementById('password-forgot');
	var meter = document.getElementById('password-strength-meter');

	password.addEventListener('input', function() {
	  var val = password.value;
	  var result = zxcvbn(val);
	
	  // Update the password strength meter
	  meter.value = result.score;
	});
})
