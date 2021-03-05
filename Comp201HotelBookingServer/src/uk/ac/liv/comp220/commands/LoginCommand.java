package uk.ac.liv.comp220.commands;

import database.hibernate.DatabaseConnector;
import model.Receptionist;
import model.Role;
import model.User;

public class LoginCommand extends CommandBase {

	public static final String name="login";
	
	@Override
	protected ServerResponse onExecute() {
		ServerResponse response=new ServerResponse(ResponseCode.PENDING);
		String username = arguments.get("username");
		String password = arguments.get("password");
		System.out.println("username is..."+username);
		if (!((username != null) && (username.length() > 0))) {
			response = new ServerResponse(ResponseCode.USERN_MISSING);
			return (response);
		}
		Login login=new Login(username);
		login.checkPassword(password);		
		if (!login.isBad()) {
			User currentUser=login.getUser();
			Role role=currentUser.getRole();
			int id=currentUser.getUserId();
			switch (role) {
			case RECEPTIONIST :
				currentUser=new Receptionist();
				DatabaseConnector.loadObject(currentUser,new Integer(id)); // load it up
				break;				
			}
			setCurrentUser(currentUser);
		   response=new ServerResponse(ResponseCode.OK);
		} else {
		   response=new ServerResponse(ResponseCode.BAD_USERNAME_PASSWORD);
		}
		return(response);
	}
}
