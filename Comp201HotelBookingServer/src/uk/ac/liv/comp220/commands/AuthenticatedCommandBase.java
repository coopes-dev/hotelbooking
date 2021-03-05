package uk.ac.liv.comp220.commands;

import model.Role;

/**
 * For every command that requires the user to be logged in
 * This class should be extended
 * Each command in turn, is responsbile for check if the security level is correct for that command
 * @author coopes
 *
 */
public abstract class AuthenticatedCommandBase extends CommandBase {

	
	
	protected abstract ServerResponse onAuthenticatedExecute();
	
	/**
	 * Returns roles allowed to execute this command
	 * @return
	 */
	protected abstract Role [] getRoles();
	
	
	@Override
	protected ServerResponse onExecute() {
		// First check we are logged in
		if (currentUser==null) {
			return(new ServerResponse(ResponseCode.UNAUTHORISED));	// failed to get an authorisation list
		}
		// Now get the roles
		Role [] roles=getRoles();
		boolean ok=false;
		for (int idx=0;idx<roles.length;idx++) {
			if (roles[idx].equals(currentUser.getRole())) {
				ok=true;
				break;
			}
		}
		if (!ok) {
			return(new ServerResponse(ResponseCode.UNAUTHORISED));	// failed to get correct role for command
		}
		return(onAuthenticatedExecute());
	}
	

}
