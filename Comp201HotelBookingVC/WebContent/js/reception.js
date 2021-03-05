/*
	Various commands for the receptionist
*/


var Reception={};   // singleton instance

// Various commands for the receptionist

if (!Reception.instance) {
	   $( function() {
	    $( "#datepickerStart" ).datepicker({dateFormat: "dd-mm-yy"});
	  } );
	   $( function() {
		    $( "#datepickerEnd" ).datepicker({dateFormat: "dd-mm-yy"});
	  } );
        Reception.instance =new Object();  // class is singleton

        Reception.instance.showPanel=function(panelId) {
        	$(".mainPanel").each(function () {
                if ($(this).attr("id")==panelId) {
                	$(this).css("display","block");
                } else {
                	$(this).css("display","none");
                }
        	});
            
        }
        
        Reception.instance.countRoomTypes=function(list) {
        	var types=new Object();
        	for (var idx=0;idx<list.length;idx++) {
        		var room=list[idx];
        		var description=room.roomDescription.description;
        		if (!types[description]) {
        			types[description]=new Object();
        			types[description].count=1;	// 1 room of this description
        			types[description].description=description;	// 1 room of this description        			
        			types[description].maxOccupancy=room.roomDescription.maxOccupancy;
        			types[description].tarrif=room.roomDescription.tarrif.value;
        		} else {
        			types[description].count++;
        		}        		
        	}
        	return(types);
        }
        
        Reception.instance.search=function() {
        	var parameters=new Array();
        	parameters["startDate"]=$("#datepickerStart").val();
        	parameters["endDate"]=$("#datepickerEnd").val();

        	function moneyFormat(cash) {
        		var ret="";
        		ret=ret+cash % 100;
        		if (ret.length==1) ret="0"+ret;
        		ret=Math.floor(cash/100)+"."+ret;
        		return(ret);
        	}
        	
        	this.onSendRequest("getavailable",parameters,function(response) {  
        		if (response.responseData) {
        			if (response.responseData.data) {
        				list=response.responseData.data;
        				var roomAvailable=Reception.instance.countRoomTypes(list);
        				var responseString="";
        				for (const roomType in roomAvailable) {
        					room=roomAvailable[roomType];
        					responseString=responseString+room.count+"  "+room.description+" rooms available, max occupancy per room "+room.maxOccupancy+" tarrif is £"+moneyFormat(room.tarrif)+"<br/><br/>";
        				}
        				$("#roomsAvailable").html(responseString);	// write back to page
        			}
        		}
        		translatePage();
        	});
        }
        
        
        Command.apply(Reception.instance); // Inherits from command
        Reception.instance.setUpPage = function () {
        	//alert("Setting up reception page..");
        	var parameters=new Array();
        	this.onSendRequest("getroomlist",parameters,function(response) {        		
        		translatePage();
        	});
            
        	
        }
        
            
        Reception.instance.logout = function () {
        	var parameters=new Array();
        	this.onSendRequest("logout",parameters,function(response) {        		
        		window.location.href="login.html";
        	});
                
        }
}

	
