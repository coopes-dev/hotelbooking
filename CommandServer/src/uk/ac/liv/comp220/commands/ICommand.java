package uk.ac.liv.comp220.commands;

import java.util.HashMap;

public interface ICommand {
	public ServerResponse Execute(HashMap <String,String> arguments);
}
