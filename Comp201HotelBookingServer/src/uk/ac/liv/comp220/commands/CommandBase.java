package uk.ac.liv.comp220.commands;

import java.util.HashMap;

import model.User;

public abstract class CommandBase implements ICommand {

	protected HashMap <String,String> arguments=new HashMap <String,String>();
	protected User currentUser;
	
	@Override
	public ServerResponse Execute(HashMap <String,String> arguments) {
		this.arguments=arguments;
		ServerResponse response=null;
		try {
		     response=onExecute();
		} catch (Exception commandException) {
			response=new ServerResponse(ResponseCode.EXCEPTION,commandException.getMessage());
			commandException.printStackTrace();
		}
		if (getCurrentUser()!=null) {
			response.setSessionStatus("loggedin");
			response.getResponseData().setRole(getCurrentUser().getRole()); // send Role back to client
		} else {
			response.setSessionStatus("loggedout");
		}
		return(response);
	}
	
	/**
	 * Return the current user
	 */
	public User getCurrentUser() {
		return(this.currentUser);
	}

	protected abstract ServerResponse onExecute();

	public void setCurrentUser(User currentUser) {
		this.currentUser=currentUser;
	}
	

}
