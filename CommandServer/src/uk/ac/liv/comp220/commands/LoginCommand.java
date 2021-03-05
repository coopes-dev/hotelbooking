package uk.ac.liv.comp220.commands;

import java.net.URLDecoder;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import acme.crypto.SecurityUtil;
import uk.ac.liv.comp220.hibernate.DatabaseConnector;
import uk.ac.liv.comp220.user.User;

public class LoginCommand extends CommandBase {

	public static final String name="login";
	
	@Override
	protected ServerResponse onExecute() {
		ServerResponse response=new ServerResponse(ResponseCode.PENDING);
		String username = arguments.get("username");
		@SuppressWarnings("deprecation")
		String password = arguments.get("password");	    	
		if (!((username != null) && (username.length() > 0))) {
			response = new ServerResponse(ResponseCode.USERN_MISSING);
			return (response);
		}
		Login login=new Login(username);
		login.checkPassword(password);		
		if (!login.isBad()) {		
		   response=new ServerResponse(ResponseCode.OK);
		} else {
		   response=new ServerResponse(ResponseCode.BAD_USERNAME_PASSWORD);
		}
		return(response);
	}
}
