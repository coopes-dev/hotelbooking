package uk.ac.liv.comp220.commands;

import java.util.HashMap;

public abstract class CommandBase implements ICommand {

	protected HashMap <String,String> arguments=new HashMap <String,String>();
	
	@Override
	public ServerResponse Execute(HashMap <String,String> arguments) {
		this.arguments=arguments;
		return(onExecute());
	}

	protected abstract ServerResponse onExecute();
	

}
