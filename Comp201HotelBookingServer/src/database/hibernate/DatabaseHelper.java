package database.hibernate;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Booking;
import model.Guest;
import model.Hotel;
import model.Money;
import model.Receptionist;
import model.Role;
import model.Room;
import model.RoomBooking;
import model.RoomDescription;
import model.User;

/**
 * This is used to check the connection to the database and allow the user to configure it
 * @author coopes
 *
 */
public class DatabaseHelper {

	
	// Fetch's a room instance from the datebase for a given hotel and a given room number
	private Room getHotelRoom(int roomNumber,Hotel hotel) {
		
		String hql = "FROM Room R where R.roomNumber='" + roomNumber + "' and R.hotel.hotelId='"+hotel.getHotelId()+"'";
		Session session = DatabaseConnector.getFactory().openSession();
		Query <Room> query = session.createQuery(hql, Room.class);
		List<Room> results = query.list();
		if (results.size() > 0) {
			return(results.get(0));
		}	
		return(null);
	}
	
	
	private void setUpTestDatabase() {
		DatabaseConfig config=new DatabaseConfig();
		if (DatabaseConnector.testConnection(config.getHibernateConfigDrop()).isOk()) {			
			Hotel hotel=new Hotel("Quiet Night Hotel");
			DatabaseConnector.saveObject(hotel);
			System.out.println("Hotel id is "+hotel.getHotelId());
			// Add in descriptions for room's (Room types)
			RoomDescription singleRoom=new RoomDescription("Single",1,new Money(7500));
			RoomDescription doubleRoom=new RoomDescription("Double",2,new Money(11000));
			RoomDescription premierRoom=new RoomDescription("Premier",2,new Money(14500));
			RoomDescription executiveRoom=new RoomDescription("Executive",3,new Money(17500));
			DatabaseConnector.saveObject(singleRoom);
			DatabaseConnector.saveObject(doubleRoom);
			DatabaseConnector.saveObject(premierRoom);
			DatabaseConnector.saveObject(executiveRoom);
			//Now add in rooms, first the singles
			for (int roomNo=1;roomNo<=10;roomNo++) {
				Room room=new Room(roomNo,hotel,singleRoom);
				DatabaseConnector.saveObject(room);
			}
			for (int roomNo=11;roomNo<=20;roomNo++) {
				Room room=new Room(roomNo,hotel,doubleRoom);
				DatabaseConnector.saveObject(room);
			}
			// Then the premier
			for (int roomNo=21;roomNo<=27;roomNo++) {
				Room room=new Room(roomNo,hotel,premierRoom);
				DatabaseConnector.saveObject(room);
			}
			// Then the executive
			for (int roomNo=28;roomNo<=30;roomNo++) {
				Room room=new Room(roomNo,hotel,executiveRoom);
				DatabaseConnector.saveObject(room);
			}
			// Now add in the receptionist for booking
			Receptionist userReceptionist=new Receptionist("coopes@liverpool.ac.uk","seb","password1234",hotel);
			DatabaseConnector.saveObject(userReceptionist);
			
			// We will know manually add in a guest and a booking with some rooms to book
			// First make a guest..
			
			Guest guest=new Guest("Jones", "Bill","S",24,5, 1980);
			guest.setEmail("coopes@liv.ac.uk");
			DatabaseConnector.saveObject(guest);
			
			// Now the booking..
			// Create with this guest... at today's data
			Booking booking=new Booking(new Date(System.currentTimeMillis()),guest);
			DatabaseConnector.saveObject(booking);
			
			// Now load up a room to put in the room booking
	
			Room room=getHotelRoom(7,hotel);	// fetch room number 7
			RoomBooking roomBooking=new RoomBooking(booking,room,2,2,2020,5,2,2020);
			DatabaseConnector.saveObject(roomBooking); // save room booking against the booking and room
			room=getHotelRoom(17,hotel);	// fetch room number 17			
			roomBooking=new RoomBooking(booking,room,17,1,2021,20,1,2021); 
			DatabaseConnector.saveObject(roomBooking); // save room booking against the booking and room			
	
			roomBooking=new RoomBooking(booking,room,22,8,2020,24,8,2020); 
			DatabaseConnector.saveObject(roomBooking); // save room booking against the booking and room			
	
			
			LocalDate startDate=LocalDate.of(2021,1,10);
			LocalDate endDate=LocalDate.of(2021,1,20);
			
			boolean [] available=room.getAvailability(startDate,endDate);
			for (int idx=0;idx<available.length;idx++) {
				System.out.println("Available for "+startDate+" is "+available[idx]);
				startDate=startDate.plusDays(1);
			}
		}
	}
	
	private void testConnection() {
		DatabaseConfig config=new DatabaseConfig();
		if (!DatabaseConnector.testConnection(config.getHibernateConfig()).isOk()) {
			DatabaseConfigDialog dialog=new DatabaseConfigDialog();
			System.exit(1);
		}		
	}
	
	
	public static void main(String[] args) {
		DatabaseHelper helper=new DatabaseHelper();
		
		helper.testConnection();
		helper.setUpTestDatabase();
		
	}

}
