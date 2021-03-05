package uk.ac.liv.comp220.commands;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import database.hibernate.DatabaseConnector;
import model.Booking;
import model.Guest;
import model.Role;

public class AddBooking extends AuthenticatedCommandBase {
	public static final String name="addBooking";

	@Override
	protected ServerResponse onAuthenticatedExecute() {
		ServerResponse response=new ServerResponse(ResponseCode.OK);
		int guestID=Integer.parseInt(arguments.get("guestID"));
		String hql="From Guest  G where G.personID="+guestID;
		Session session = DatabaseConnector.getFactory().openSession();
		Query<Guest> query = session.createQuery(hql, Guest.class);
		List<Guest> results = query.list();
        if (results.size()>0) {
        	Guest guest=results.get(0);	// get first n list and attach to book
        	Date now=new Date(System.currentTimeMillis());
        	Booking booking=new Booking(now,guest);
        	DatabaseConnector.saveObject(booking); // save on Database
        	response.getResponseData().setData(booking); // send booking back to UI
        				
        } else {
        	// bad guest id cannot find
        	response=new ServerResponse(ResponseCode.GUEST_NOT_FOUND);
        }
		return(response);
	}

	@Override
	protected Role[] getRoles() {
		Role [] roles= {Role.RECEPTIONIST};
		return(roles);
	}
	
	

}
