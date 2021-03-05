package uk.ac.liv.comp220.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;
import org.hibernate.query.Query;

import database.hibernate.DatabaseConnector;
import model.Booking;
import model.Guest;
import model.Role;
import model.RoomBooking;
import model.User;

public class GetObjectList extends AuthenticatedCommandBase {
	public static final String name="getObjectList";

	@Override
	protected ServerResponse onAuthenticatedExecute() {
		ServerResponse serverResponse=new ServerResponse(ResponseCode.OK);
		// This command serves a range of object to the receptionist
		// So it provides a list of guests or a list of bookings associated with a guest
		// or a list of room bookings and meal bookings associated with book
		// Make new guest object
		String objectType=arguments.get("objectType");
		if (objectType.equals("Booking")) {		// fetching details of booking
			int bookingId=Integer.parseInt(arguments.get("bookingId"));
			getRoomBookings(serverResponse, bookingId);
		}
			
		if (objectType.equals("Guest")) {
			Guest guest=null;
			int guestId=Integer.parseInt(arguments.get("guestId"));
			Session session=DatabaseConnector.getFactory().openSession();
			if (guestId==0) {
				CriteriaBuilder cb = session.getCriteriaBuilder();
				CriteriaQuery<Guest> cr = cb.createQuery(Guest.class);
				Root<Guest> root = cr.from(Guest.class);
				cr.select(root);
				Query<Guest> query = session.createQuery(cr);
				System.out.println("Quesry is "+query.getQueryString());
				List<Guest> results = query.getResultList();
				// we now append on the list of bookings for the first guest in list
				
				guest=results.get(0);
				
				List<Booking> bookings=getBookings(guest.getPersonID(),serverResponse);
				
				serverResponse.getResponseData().setData(results);
				serverResponse.getResponseData().setBookings(bookings); // send back bookings for first customer
				
			} else {
				List<Booking> bookings=getBookings(guestId,serverResponse);				
				serverResponse.getResponseData().setBookings(bookings); // send back bookings for first customer
			}
				
			return(serverResponse);
		}
		return(serverResponse);
	}

	private void getRoomBookings(ServerResponse serverResponse, int bookingId) {
		Session session=DatabaseConnector.getFactory().openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<RoomBooking> cr = cb.createQuery(RoomBooking.class);
		Root<RoomBooking> root = cr.from(RoomBooking.class);
		
		Predicate[] predicates = new Predicate[1];
		
		predicates[0] = cb.equal(root.get("booking").get("bookingId"),bookingId);			// fetch all room bookings
		cr.select(root).where(predicates);
		Query<RoomBooking> query = session.createQuery(cr);
		List<RoomBooking> results = query.getResultList();
		serverResponse.getResponseData().setRoomBookings(results);
	}

	private List<Booking> getBookings(int guestId,ServerResponse response) {
        String hql="From Booking  B where B.guest.personID="+guestId;
        Session session = DatabaseConnector.getFactory().openSession();
		Query<Booking> query = session.createQuery(hql, Booking.class);
		List<Booking> results = query.list();
		Booking booking=results.get(0);
		getRoomBookings(response,booking.getBookingId());
		return results;
	}

	@Override
	protected Role[] getRoles() {
		Role [] roles= {Role.RECEPTIONIST};
		return(roles);
	}
	

}
