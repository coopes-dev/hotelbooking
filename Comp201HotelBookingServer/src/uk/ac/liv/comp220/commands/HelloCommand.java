package uk.ac.liv.comp220.commands;

/**
 * This is general purpose command, called when loading a page up
 * @author coopes
 *
 */
public class HelloCommand extends CommandBase {

	public static final String name="hello";
	
	@Override
	protected ServerResponse onExecute() {
		
		ServerResponse response=new ServerResponse(ResponseCode.OK);
		return(response);
	}
	
	
	
	
	

}