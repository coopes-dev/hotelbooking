/**
 * $LastChangedDate: 2020-03-12 12:27:42 +0000 (Thu, 12 Mar 2020) $
 $Rev: 38 $
 $Author: sebdunes $
 */

package uk.ac.liv.comp220.commands;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import uk.ac.liv.comp220.hibernate.DatabaseConnector;
import uk.ac.liv.comp220.user.Role;
import uk.ac.liv.comp220.user.User;

public class RegisterCommand extends CommandBase {

	public static final String name = "register";
	public static final int MIN_PASSWORD_LENGTH = 8;

	/**
	 * Checks if argument for commands are ok If return is OK, then no errors,
	 * otherwise returns error response
	 * 
	 * @return OK if ok, otherwise returns error response
	 */
	private ServerResponse validateArguments(String username, String password) {
		//
		ServerResponse response = new ServerResponse(ResponseCode.OK);
		if (!((username != null) && (username.length() > 0))) {
			response = new ServerResponse(ResponseCode.USERN_MISSING);
			return (response);
		}
		if (!((password != null) && (password.length() > 0))) {
			response = new ServerResponse(ResponseCode.PASSWORD_MISSING);
			return (response);
		}
		if (password.length() < MIN_PASSWORD_LENGTH) {
			response = new ServerResponse(ResponseCode.PASSWORD_TOO_SHORT,
					"{TEXT_PASSWORD_TOO_SHORT}" + MIN_PASSWORD_LENGTH + "{TEXT_CHARACTERS}");
			return (response);
		}
		// Now we look for uppercase letters in password
		boolean isOK = false;
		for (int index = 0; index < password.length(); index++) {
			if (Character.isUpperCase(password.charAt(index))) {
				isOK = true;
				break;
			}
		}
		if (!isOK) {
			response = new ServerResponse(ResponseCode.PASSWORD_MISSING_UPPER_CASE);
			return (response);
		}
		// Now we look for lowercase letters in password
		isOK = false;
		for (int index = 0; index < password.length(); index++) {
			if (Character.isLowerCase(password.charAt(index))) {
				isOK = true;
				break;
			}
		}
		if (!isOK) {
			response = new ServerResponse(ResponseCode.PASSWORD_MISSING_LOWER_CASE);
			return (response);
		}
		// Now we look for digits in password
		isOK = false;
		for (int index = 0; index < password.length(); index++) {
			if (Character.isDigit(password.charAt(index))) {
				isOK = true;
				break;
			}
		}
		if (!isOK) {
			response = new ServerResponse(ResponseCode.PASSWORD_MISSING_DIGIT);
			return (response);
		}
		return (response);
	}

	@Override
	protected ServerResponse onExecute() {
		ServerResponse response = new ServerResponse(ResponseCode.OK);
		String username = arguments.get("username");
		String password = arguments.get("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		response = validateArguments(username, password);
		if (response.getResponseCode() != ResponseCode.OK.ordinal()) {
			return (response); // error validating arguments
		}
		String hql = "FROM User U where U.username='" + username + "'";
		Session session = DatabaseConnector.getFactory().openSession();

		Query<User> query = session.createQuery(hql, User.class);
		List<User> results = query.list();
		System.out.println("size of list is " + results.size());
		if (results.size() > 0) {
			response = new ServerResponse(ResponseCode.USER_EXISTS);
			return (response);
		}
		int role = Integer.parseInt(arguments.get("role"));
		user.setPassword(password);
		Role urole = Role.values()[role];
		user.setRole(urole);
		DatabaseConnector.saveObject(user);
		return (response);
	}

}
