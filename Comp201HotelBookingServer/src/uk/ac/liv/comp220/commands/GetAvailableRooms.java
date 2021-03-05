package uk.ac.liv.comp220.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import database.hibernate.DatabaseConnector;
import model.Hotel;
import model.Receptionist;
import model.Role;
import model.Room;

public class GetAvailableRooms extends AuthenticatedCommandBase {

	public static final String name="getavailable";
	
	// Fetch's a list of room instances from the datebase for a given hotel
		private List<Room> getHotelRooms(Hotel hotel) {
			
			String hql = "FROM Room R where R.hotel.hotelId='"+hotel.getHotelId()+"'";
			Session session = DatabaseConnector.getFactory().openSession();
			Query <Room> query = session.createQuery(hql, Room.class);
			List<Room> results = query.list();
			return(results);
		}

	
	@Override
	protected ServerResponse onAuthenticatedExecute() {
		ServerResponse response=new ServerResponse(ResponseCode.OK);
		// First load up the start Date and end Date parameters..
		String startDates=this.arguments.get("startDate");
		String endDates=this.arguments.get("endDate");
		Receptionist receptionist=(Receptionist)currentUser;
		List<Room> list=this.getHotelRooms(receptionist.getHotel()); // fetch lisy of all hotel rooms		
		if (startDates.equals("") || (endDates.equals(""))) {
			response.getResponseData().setData(list);
			return(response);		
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate startDate=LocalDate.parse(startDates,formatter);
		LocalDate endDate=LocalDate.parse(endDates,formatter);
		System.out.println("Start date is "+startDate.toString());
		System.out.println("End date is "+startDate.toString());
		
		ArrayList <Room> availableRooms=new ArrayList<Room>();
		for (int idx=0;idx<list.size();idx++) {
			Room room=list.get(idx);
			if (room.isAvailable(startDate, endDate)) {
				availableRooms.add(room);
			}
		}
		response.getResponseData().setData(availableRooms);		
		return(response);		
		
		
	}

	@Override
	protected Role[] getRoles() {
		Role [] roles= {Role.RECEPTIONIST};
		return(roles);
	}

}
