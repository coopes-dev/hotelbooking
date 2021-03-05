package uk.ac.liv.comp220.commands;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.google.common.hash.Hashing;

import database.hibernate.DatabaseConnector;
import model.User;



/**
 * # This class handles a general purpose login protocol
 * This version is a simple PAP (Password Authentication) protocol
 * TO DO ... extend to add in different types of protocol e.g. CHAP
 * @author coopes
 *
 */
public class Login {
	private String username="";
	private String password="";
	// States for login, OK means logged in ok
	// PENDING means waiting for credientials
	// OK means logged in OK
	// FAIL means bad command or user name
	private static final int START=0;
	private static final int PENDING=1;
	private static final int OK=2;
	private static final int FAIL=3;
	
	private int state=START;
	private User user;
	
	public Login(String username) {
		this.username=username;
		init();
	}
	
	public void checkPassword(String password) {
		password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
		if (!this.password.equals(password)) {
			state=FAIL;
		} else {
			state=OK;
		}
	}
	
	
	public boolean isBad() {
		return(state==FAIL);
	}
	
	
	private void init() {
		String hql = "FROM User U where U.username='" + username + "'";
		//Session session = DatabaseConnector.getFactory().openSession();
		Session session = DatabaseConnector.getFactory().openSession();
		Query<User> query = session.createQuery(hql, User.class);
		List<User> results = query.list();
		System.out.println("size of list is " + results.size());
		if (results.size() > 0) {
			this.user=results.get(0);
			password=user.getPassword();
		} else {
			state=FAIL;
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
