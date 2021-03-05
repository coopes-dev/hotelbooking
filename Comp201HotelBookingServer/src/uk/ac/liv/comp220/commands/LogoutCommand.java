package uk.ac.liv.comp220.commands;

public class LogoutCommand extends CommandBase {
	
	/**
	 * Name of command
	 */
	public static final String name="logout";

	@Override
	protected ServerResponse onExecute() {
		this.currentUser=null;					// Clear out user, to enable cleared out session
		return(new ServerResponse(ResponseCode.OK));
	}
	
	
	

}
