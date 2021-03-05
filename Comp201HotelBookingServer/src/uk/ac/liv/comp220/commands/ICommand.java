package uk.ac.liv.comp220.commands;

import java.util.HashMap;

import model.User;

public interface ICommand {
	public ServerResponse Execute(HashMap <String,String> arguments);
	
	public User getCurrentUser();
	
	public void setCurrentUser(User currentUser);
}
