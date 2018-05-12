$('#btnCreate').on("click", function(event){
	var pass = $('input[name=newPass]').val();
    var repass = $('input[name=newPassConfirm]').val();
    if(pass == repass){
    	$('#createAccount').sumbit();
    	alertify.success('Account Creation Successful!'); 
    	alertify.warning('An email has been sent to your email address to activate your account. Your account needs to be authenticated by the administrators before use.');
    	return true;
    }
    else{
    	event.preventDefault();
    	alertify.error('Account Creation Unsuccessful: Passwords do not match'); 
    	return false;
    }
})