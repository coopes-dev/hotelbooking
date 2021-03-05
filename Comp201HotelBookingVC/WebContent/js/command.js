/**
 * Created by customer on 20/10/2017.
 */

/**
 * Command handling code
 *
 */


var serverInfo=null;        // Singleton of server info used for configuration data
var Command=function() {

    var url="";



    // Points to command server
    var baseURL="http://localhost:8080/Comp201HotelBookingServer/Command";

    this.debug=true;
    this.testMode=false;
    this.doAjaxCall = function(data,success,failure) {

        var requestTimeout=20000; // after 20 seconds no response then timeout


        $.ajaxSetup ({
            cache: false
        });


        var request=$.ajax({
            type: "GET",
            url: baseURL,
            data: data,
            crossDomain: true,
            dataType: "text",
            cache: false,
            xhrFields: {
                withCredentials: true
             },
            timeout: requestTimeout,
            error : function(xml,textStatus,errorThrown) {
                failure();
            },
            success: function(msg){
                try {
				    success(msg);
                } catch (error) {
				}
            }
        });
    };





    this.onExecute=function() {
        alert("Error on Execute not over-ridden")
    }

    this.execute=function() {
        this.onExecute();
    }

    /**
     * Called when page is loading up to say hello to server and get configuration information
     */
    this.onLoad=function(loadedCallback) {
    	var parameters=new Array();
        this.onSendRequest("hello",parameters,function(response) {
        	if (loadedCallback) {
        	   loadedCallback(response);  // page loaded up
        	}
        	translatePage();
        });
    }
    

    this.onSendRequest =function(command,parameters,callback) {
        var data="name="+command+"&";
        var debug=false;
        // Now add in the arguments
        if (serverInfo==null) {
            parameters["getconfig"]="true";
        } else {
        }
        
        for (var key in parameters){
                data=data+key+"="+encodeURIComponent(parameters[key])+"&";
        }
        //alert("data is "+data);
        if (window.console) {
            console.debug("Request is "+data);
        }

        this.doAjaxCall(data,
            function(msg) {
                this.response=null;
                try {
                    this.response=JSON.parse(msg);
                    this.responseCode=parseInt(this.response.responseCode);
                    this.responseMessage='{'+this.response.responseMessage+'}';
                    if (serverInfo==null) {
                        if (this.response.serverInfo) {
                            serverInfo=this.response.serverInfo;
                        }
                    }
                    if (this.responseCode==serverInfo.responses['ResponseCode:UNAUTHORISED']) {
                    	window.location.href="login.html"; // oops user no authorised go back to login
                    }
                } catch (error) {
                    this.responseCode=this.GENERAL_ERROR;
                    this.responseMessage="JSON parse error "+msg;
                }

                if (window.console) {
                    console.debug("Response is "+msg);
                }
                //alert("role is "+this.response.responseData.role);
                if (this.responseCode==this.GENERAL_ERROR) {
                    if (debug) {
                       // alert("new loc is "+baseURL+"?"+data);
                        window.location.href=baseURL+"?"+data;
                        return;
                    }
                }

                //alert("code is "+this.responseCode);
                callback(this.response,this);

            },
            function() {
                alert("Command failure");
            });

    }
}