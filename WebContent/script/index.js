function displayCatalog(){
	console.log("Hello!");
	
	var feed = document.getElementById("feed");
	
	$.ajax({
		context: this,
		url: 'getCatalog',
		type: 'get',
		cache: false,
		success: function(data){
			$(feed).append(data);
			console.log("displayCatalog ajax complete!");
		},
		error:function(){
			console.log("something is wrong on displayCatalog");
		}
	});
}

function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}

function showError() {
    $("#errormsg").show();
    $(".form-group").addClass("has-error");
}

function accPassMismatch(data){
	console.log("data is: " + data);
	if(data == "PASS-LOGIN-CUSTOMER"){
		document.location.href = 'Index.jsp';
	}
	else if(data == "FAIL-LOGIN-CUSTOMER"){
		console.log("wrong password");
		showError();
	}
}
function submitTheForm(email, password){
    $.ajax({
 	    context: this,
        url:'login',
        data:{'email': email,
        	  'password': password},
        type:'POST',
        cache:false,
        success: function(data){
        	console.log("submitTheForm success!");
        	accPassMismatch(data);
        },
        error:function(){
        	console.log("error searchResult.js");
        }
     });
}


$("document").ready(function(){
	
    // view book
    $(document).on("click", ".bookLink", function(){
    	console.log("book link was clicked!");
    	var bookID = $(this).attr("data-bookId");
    	console.log("book id is " + bookID);
    	window.location = "viewBook?bookID=" + bookID;  
    });
    
	displayCatalog();
    
    // submit form for button
	$(document).on("click", "#btn-signin",function() {
		console.log("Sign in clicked");
		
		var email = document.getElementById('email').value;
        var password = document.getElementById('password').value;
        
        if(validateEmail(email) && password !== null && password !== ""){
        	$(".form-group").removeClass("has-error");
        	submitTheForm(email, password); 
        } else{
        	showError();
        }
	});
    
    // submit for on enter
//    $(document).keyup(function (e) {
//		var email = document.getElementById('email').value;
//        var password = document.getElementById('password').value;
//        
//        if(validateEmail(email) && password !== null && password !== ""){
//        	$(".form-group").removeClass("has-error");
//        	submitTheForm(email, password); 
//        } else{
//        	showError();
//        }
//    });
});