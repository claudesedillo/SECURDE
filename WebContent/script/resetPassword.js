/**
 *  TODO parse inputs to string, append, then compare to actual code
 */

$("document").ready(function(){
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
