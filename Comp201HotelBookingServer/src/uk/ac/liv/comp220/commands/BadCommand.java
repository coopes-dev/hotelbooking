package uk.ac.liv.comp220.commands;



public class BadCommand extends CommandBase {

	@Override
	protected ServerResponse onExecute() {
		return(new ServerResponse(ResponseCode.INVALID_COMMAND));
	}

	
}
