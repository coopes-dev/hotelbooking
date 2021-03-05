package uk.ac.liv.comp220.commands;


public class HelloCommand extends CommandBase {

	public static final String name="hello";
	
	@Override
	protected ServerResponse onExecute() {
		ServerResponse response=new ServerResponse(ResponseCode.OK);
		
		return(response);
	}
	
	
	
	
	

}