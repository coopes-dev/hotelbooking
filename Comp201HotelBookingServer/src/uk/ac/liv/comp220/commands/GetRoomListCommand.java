package uk.ac.liv.comp220.commands;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import database.hibernate.DatabaseConnector;
import model.Hotel;
import model.Receptionist;
import model.Role;
import model.Room;

public class GetRoomListCommand extends AuthenticatedCommandBase {

	public static final String name="getroomlist";
	
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
		// We can get a room list for the receptionist... based on the hotel
		Receptionist receptionist=(Receptionist)currentUser;
		List<Room> list=this.getHotelRooms(receptionist.getHotel());
		response.getResponseData().setData(list);
		return(response);
	}

	@Override
	protected Role[] getRoles() {
		Role [] roles= {Role.RECEPTIONIST};
		return(roles);
	}

}
