/**
 *   Code to allow manipulation of guest date on back end server 
 */


var Guest={};   // singleton instance
    if (!Guest.instance) {
    	Guest.instance={};
    	
    	function moneyFormat(money) {
    		var ret="";
    		var cash=money.value;
    		
    		ret=ret+cash % 100;
    		if (ret.length==1) ret="0"+ret;
    		ret=Math.floor(cash/100)+"."+ret;
    		if (money.currencyCode=="GBP") {
    			ret="£"+ret;
    		}
    		if (money.currencyCode=="USD") {
    			ret="$"+ret;
    		}
    		if (money.currencyCode=="EUR") {
    			ret="€"+ret;
    		}
    		
    		return(ret);
    	}
    	
    	Command.apply(Guest.instance); // Inherits from command
    	
    	Guest.instance.guestChange=function() {
    		var selectedId=$('#guestChooser').val();
        	Guest.instance.getGuestList(selectedId);	// each time the guest changes update the list and bookings
    	}
    	
    	Guest.instance.getBookingList=function(bookingId) {
    		parameters["objectType"]="Guest";
    		parameters["bookingId"]=bookingId;    		
    		this.onSendRequest("getObjectList",parameters,function(response) {
    			
    		});
    	}
    	
    	Guest.instance.formatDate=function(localDateObject) {
    		return(""+localDateObject.date.day+"/"+localDateObject.date.month+"/"+localDateObject.date.day);
    	}

    	Guest.instance.deleteRoomBooking=function(roomBookingId) {
    		alert("deleting room booking "+roomBookingId);
    	}
    	
    	Guest.instance.updateBooking =function(roomBookings) {
    		var table = document.getElementById("roomBookings");	
    		var rowCount = table.rows.length;
    		for(var i=1; i<rowCount; i++) {
				table.deleteRow(1);
    		}
    		for (var idx=0;idx<roomBookings.length;idx++) {
    			var roomBooking=roomBookings[idx];
    			var row=table.insertRow(1);
    			var newcell	= row.insertCell(0);
    			newcell.innerHTML = '<input  type="button" id="deleteRoomBooking" value="Delete Room Booking" onclick="Guest.instance.deleteRoomBooking('+roomBooking.roomBookingId+')"></input>';    			
    			newcell	= row.insertCell(0);
    			
    			newcell.innerHTML = Guest.instance.formatDate(roomBooking.endDate);
    			newcell	= row.insertCell(0);
    			newcell.innerHTML = Guest.instance.formatDate(roomBooking.startDate);
    			newcell	= row.insertCell(0);
    			newcell.innerHTML = moneyFormat(roomBooking.room.roomDescription.tarrif);
    			newcell	= row.insertCell(0);
    			newcell.innerHTML =roomBooking.room.roomDescription.maxOccupancy;
    			newcell	= row.insertCell(0);
    			newcell.innerHTML=roomBooking.room.roomDescription.description;
    			newcell	= row.insertCell(0);
    			newcell.innerHTML = roomBooking.room.roomNumber;
    			
    		}
    	}
    	
    	Guest.instance.getGuestList=function(selectedId) {
    		var parameters=new Array();
    		if (!selectedId) {			// no quest selected so choose all of them
    			selectedId=0;
    		}
    		parameters["objectType"]="Guest";
    		parameters["guestId"]=selectedId;    		    		
    		this.onSendRequest("getObjectList",parameters,function(response) {
    			//alert("Response is "+JSON.stringify(response));
    			if (response.responseData.data) {	// guest list update
    			$('#guestChooser').find('option').remove().end();
    			for (var idx=0;idx<response.responseData.data.length;idx++) {
    				var item=response.responseData.data[idx];
    				var o = new Option(item.forename1+" "+item.surname,item.personID);
    				/// jquerify the DOM object 'o' so we can use the html method
    				$(o).html(item.forename1+" "+item.surname);
    				$("#guestChooser").append(o);
    			}
    			}
    			$('#bookingChooser').find('option').remove().end();
    			for (var idx=0;idx<response.responseData.bookings.length;idx++) {
    				var item=response.responseData.bookings[idx];
    				//alert("id is "+item.bookingId);
    				var o = new Option("Booking "+item.bookingId+" "+item.dateOfBooking,item.bookingId);
    				/// jquerify the DOM object 'o' so we can use the html method
    				$(o).html("Booking "+item.bookingId+" "+item.dateOfBooking);
    				$("#bookingChooser").append(o);
    				$("#bookingChooser").val(item.bookingId);
    			}
    			Reception.instance.showPanel('panelBookings');
    			if (selectedId>0) {
    				$('#guestChooser').val(selectedId);
    				
    			}
    			if (response.responseData.roomBookings) {
    				Guest.instance.updateBooking(response.responseData.roomBookings);
    			}
    		});
    	}
    	Guest.instance.addBooking=function() {
    		var parameters=new Array();            
    		var selectedGuest = $("#guestChooser").children("option:selected").val();
    		parameters["guestID"]=selectedGuest;
    		this.onSendRequest("addBooking",parameters,function(response) {
    			if (response.responseData.data) {
    			   var item=response.responseData.data;
    			   var o = new Option("Booking "+item.bookingId+" "+item.dateOfBooking,item.bookingId);
   				/// jquerify the DOM object 'o' so we can use the html method
   				   $(o).html("Booking "+item.bookingId+" "+item.dateOfBooking);
   				   $("#bookingChooser").append(o);
   				   $("#bookingChooser").val(item.bookingId);
    			}    			
    		});
    	}
    	Guest.instance.addGuest=function() {
    		var parameters=new Array();            
    		$('#panelAddGuest').children('input').each(function () {
    			parameters[this.id]=this.value;		// copy into parameters    	           
    		});
    		this.onSendRequest("addGuest",parameters,function(response) {
                $('#panelAddGuest').children('div').each(function () {
    				this.innerHTML=" ";
    			});
                $("#response").html(translateText(response.responseMessage));    
        		if (response.responseData.data) { // check if error messages present
        			$('#panelAddGuest').children('div').each(function () {
        				for (const [field,message] of Object.entries(response.responseData.data)) { 
        			    	if (this.id==field+"Error") {
        						this.innerHTML=message;	// set the message for the filed
        						
        					}
        	    					
        				  
        				}
        			});
    	    		
        		}
        	});	
    		
    			
    	}
    	
    }
