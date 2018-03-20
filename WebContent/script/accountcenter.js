$(document).ready(function() { 
	
	// edit account details
    $('#btn-editacc').click(function() {
        $('form#form-editacc input.editacc-fields').removeAttr('disabled');
        $('.combaddress-div').hide();
        $('.address-div').slideDown();
        $('form#form-editacc input.address-fields').removeAttr('disabled');
        $('#btn-editacc').hide();
        $('#save-editacc').show();
        
    })
    
    // save account details
    $('#save-editacc').click(function() {
    	$('form#form-editacc input.editacc-fields').attr('disabled', 'disabled');
        $('.combaddress-div').slideDown();
        $('.address-div').slideUp();
        $('form#form-editacc input.address-fields').attr('disabled', 'disabled');
        $('#btn-editacc').show();
        $('#save-editacc').hide();
    })
    
    // change password
    $('#btn-changepw').click(function() {
        $('.newpw-div').slideDown();
        $('form#form-changepw input.changepw-fields').removeAttr('disabled');
        $('.help-text').slideDown();
        $('#btn-changepw').hide();
        $('#save-changepw').show();
    })
    
    // save password change
    $('#save-changepw').click(function() {
        $('.newpw-div').slideUp();
        $('form#form-changepw input.changepw-fields').attr('disabled', 'disabled');
        $('.help-text').slideUp();
        $('#btn-changepw').show();
        $('#save-changepw').hide();
    })
    
    

});