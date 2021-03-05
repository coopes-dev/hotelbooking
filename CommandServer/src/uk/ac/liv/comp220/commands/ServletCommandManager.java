package uk.ac.liv.comp220.commands;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uk.ac.liv.comp220.hibernate.DatabaseConnector;

public class ServletCommandManager {
	private HttpServletRequest request;
	HttpServletResponse response;
	
	private HashMap <String,String> arguments=new HashMap <String,String>();
	private HashMap <String,String> responses=new HashMap <String,String>();
	
	
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
		String name=request.getParameter("name");
		ICommand scommand=new BadCommand();		 // falls back to bad command if not recognised
		if (name!=null) {
		switch (name) {
		case LoginCommand.name :
			scommand=new LoginCommand();
			break;
		case HelloCommand.name :
			scommand=new HelloCommand();
			break;
		case RegisterCommand.name :
			scommand=new RegisterCommand();
			break;
		default :	
				break;
			
		}
		}
		ServerResponse commandResponse=scommand.Execute(arguments); // run the command
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
