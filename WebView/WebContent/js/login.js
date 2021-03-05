/**
 * Created by customer on 20/10/2017.
 */

var Login={};   // singleton instance

Login.getInstance=function() {
    // Login command

	
	
    if (!Login.instance) {
        Login.instance =new Object();  // class is singleton

        Command.apply(Login.instance, arguments); // Inherits from command

        Login.instance.challenge=null;
        Login.instance.response=null;
        Login.instance.onExecute = function (loginPage) {
            //alert("Calling on execute...");
            var parameters=new Array();
            parameters["username"]=$("#username").val();
            var password=$("#password").val();
            if (Login.instance.response) {
            	parameters["response"]=Login.instance.response;
            	Login.instance.response=null;		// used up now so reset
            }
            this.onSendRequest("login",parameters,function(response) {

                if (response.responseCode!=serverInfo.responses['ResponseCode:OK']) {
                	// Pending means client has to send back challenge to response
                	if (response.responseCode==serverInfo.responses['ResponseCode:PENDING']) {
                		// Calculate response to challenge and then send response back
                		Login.instance.response=acme.crypto.SecurityUtil.makeResponse(response.challenge,password);
                		setTimeout(function() {
                			Login.instance.onExecute();
                		}, 1000);	// kick of another request to send back response
                		return;
                	}
                } else{
                    // "Logged in OK"
                    // Go to home page
                	$("#response").html("Logged in ok!!");
                	alert("Logged in !!!");
                    switch (response.responseData.role) {
                        case serverInfo.roles.DOCTOR :
                            alert("Doctor logged in");
                            window.location.href="doctorhome.html";
                            break;
                        case serverInfo.roles.ADMINISTRATOR :
                            alert("ADMINlogged in");
                            window.location.href="adminhome.html";
                            break;
                    }

                }
                $("#response").html(response.responseMessage);

            });

        }
    }

    return(Login.instance);

}


