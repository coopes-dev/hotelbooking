package uk.ac.liv.comp220.commands;

import java.util.HashMap;

import uk.ac.liv.comp220.user.Role;

public class ServerInfo {
	private HashMap responses;
	private HashMap roles;
	private boolean testMode=false;
	
	public ServerInfo() {
		// response codes are needed by client to work out what happened
		responses=enumToHashMap(ResponseCode.values(),ResponseCode.class);
		// user roles
		roles=enumToHashMap(Role.values(),Role.class);
	    testMode=Command.TESTMODE;
	}

	private HashMap enumToHashMap(@SuppressWarnings("rawtypes") Enum values[],Class classType) {
		HashMap returnValue=new HashMap();
		Enum eType;
		
		for (int idx=0;idx<values.length;idx++) {
			String key=classType.getSimpleName()+":"+values[idx].toString();
			String value=Integer.toString(values[idx].ordinal());
			returnValue.put(key,value);
		}
		return(returnValue);
	}
}
