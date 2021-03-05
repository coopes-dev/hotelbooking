package uk.ac.liv.comp220.commands;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.User;


/**
 * Every single action of the server, has an associated command, the command instances are generated by
 * this class
 * Each command controls its own execution, some commands require the user to be logged in, these
 * are authenticated commands
 * To add in new command, add them to the case statement of RunCommand in this class
 * 
 * @author coopes
 *
 */
public class ServletCommandManager {
	private HttpServletRequest request;
	HttpServletResponse response;
	
	private HashMap <String,String> arguments=new HashMap <String,String>();
	private HashMap <String,String> responses=new HashMap <String,String>();
	private User currentUser=null;
	
	public ServletCommandManager(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
		Enumeration <String> argumentNames=request.getParameterNames();
		
		while (argumentNames.hasMoreElements()) {
			String name=argumentNames.nextElement();
			String value =request.getParameter(name);
			
			arguments.put(name, value);		// save in command list
		}
		
		
	}
	
	public void RunCommand() {
		HttpSession session=request.getSession();
		currentUser=(User)session.getAttribute("user"); // This is logged in user
		System.out.println("Id is "+session.getId());
		if (currentUser!=null) {
			System.out.println("Got user from session....");
		} else {
			System.out.println("Got NULL user from session....");
		}
		String name=request.getParameter("name");
		System.out.println("Name of command is "+name);
		ICommand scommand=new BadCommand();		 // falls back to bad command if not recognised
		if (name!=null) {
		switch (name) {
		case LoginCommand.name :
			scommand=new LoginCommand();
			break;
		case LogoutCommand.name :
			scommand=new LogoutCommand();
			break;		
		case HelloCommand.name :
			scommand=new HelloCommand();
			break;
		case RegisterCommand.name :
			scommand=new RegisterCommand();
			break;
		case AddBooking.name :
			scommand=new AddBooking();
			break;
		case GetRoomListCommand.name :
			scommand=new GetRoomListCommand();
			break;
		case GetObjectList.name :
			scommand=new GetObjectList();
			break;
		
		case GetAvailableRooms.name :
			scommand=new GetAvailableRooms();
			break;
		case AddGuestCommand.name :
			scommand=new AddGuestCommand();
			break;
		default :	
			break;
			
		}
		}
		scommand.setCurrentUser(currentUser);
		ServerResponse commandResponse=scommand.Execute(arguments); // run the command
		if (scommand.getCurrentUser()!=null) {
			System.out.println("Writing user into session....");
		} else {
			System.out.println("Writing null into session....");
		}
		
		session.setAttribute("user",scommand.getCurrentUser());
		GsonBuilder builder = new GsonBuilder();
		builder.setVersion(2.0);
		Gson gson = builder.create();
		try {
			response.getWriter().println(gson.toJson(commandResponse));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}