/**
 *  Support code to register new user 
 */

/**
 * Created by S.Coope on 25/02/2020.
 */

var Register={};   // singleton instance, inherits from command

Register.getInstance=function() {
    // Register new user command
	
    if (!Register.instance) {
    	$( function() {
    	    $( "#datepickerStart" ).datepicker();
    	  } );
    	$( function() {
    	    $( "#datepickerEnd" ).datepicker();
    	  } );
        Register.instance =new Object();  // class is singleton

        Command.apply(Register.instance, arguments); // Inherits from command

        /** Seeds fields in page according to response
         * 
         */
        Register.instance.setUpPage=function(response) {
        	var roles=response.serverInfo.roles;
        	var selector = document.getElementById('role');
        	for (const property in roles) {
        		// create new option element
            	var option = document.createElement('option');
	            option.appendChild( document.createTextNode('{'+property+'}' ) );
	            option.className="translated";
	            option.value=roles[property];
        		selector.appendChild(option);
        	}
        }
        
        Register.instance.onExecute = function (loginPage) {
            //alert("Calling on execute...");
            var parameters=new Array();
            parameters["username"]=$("#username").val();
            parameters["password"]=$("#password").val();
            parameters["role"]=$("#role").val();
            this.onSendRequest("register",parameters,function(response) {

                if (response.responseCode!=serverInfo.responses.OK) {

                } else{
                    // "Logged in OK"
                    // Go to home page
                    switch (response.user.role) {
                        case serverInfo.roles.DOCTOR_ROLE :
                            alert("Doctor logged in");
                            window.location.href="doctorhome.html";
                            break;
                        case serverInfo.roles.ADMINISTRATOR_ROLE :
                            alert("ADMINlogged in");
                            window.location.href="adminhome.html";
                            break;
                    }

                }
                $("#response").html(translateText(this.responseMessage));

            });

        }
    }

    return(Register.instance);

}


