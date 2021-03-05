/**
 * Created by customer on 20/10/2017.
 */

var Login={};   // singleton instance

Login.getInstance=function() {
    // Login command

	
	
    if (!Login.instance) {
        Login.instance =new Object();  // class is singleton

        Command.apply(Login.instance, arguments); // Inherits from command

        Login.instance.response=null;
        Login.instance.onExecute = function (loginPage) {
            //alert("Calling on execute...");
            var parameters=new Array();
            parameters["username"]=$("#username").val();
            var password=$("#password").val();
            parameters["password"]=password;
            this.onSendRequest("login",parameters,function(response) {
                if (response.responseCode!=serverInfo.responses['ResponseCode:OK']) {
                } else{
                    // "Logged in OK"
                    // Go to home page, depending on role
                	switch (response.responseData.role) {
                        case serverInfo.roles['Role:RECEPTIONIST'] :
                            window.location.href="receptionistHome.html";
                            break;
                        case serverInfo.roles['Role:ADMINISTRATOR'] :
                            window.location.href="adminHome.html";
                            break;
                    }
                	return;

                }
                $("#response").html(translateText(response.responseMessage));

            });

        }
    }

    return(Login.instance);

}


