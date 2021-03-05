package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.Session;
import org.hibernate.query.Query;

import database.hibernate.DatabaseConnector;

@Entity
public class Room {
	
	public Room(int roomNumber, Hotel hotel, RoomDescription roomDescription) {
		super();
		this.roomNumber = roomNumber;
		this.hotel = hotel;
		this.roomDescription = roomDescription;
	}
	
	public Room() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)		
	private int roomId;
	private int roomNumber;
	
	
	@ManyToOne
	private Hotel hotel;
	
	// Many rooms share the same room description... Many --> One
	@ManyToOne
	private RoomDescription roomDescription;
	
	/**
	 * Returns an array of boolean's showing if room is available on day's between start and end date
	 * 
	 * @param startDate  Begin of the checking period
	 * @param endDate  End of the checking period
	 * @return  Array of booleans, on boolean for each day free between the periods
	 */
	public boolean [] getAvailability(LocalDate startDate,LocalDate endDate) {
		int dayCount=0;
		if (startDate.isAfter(endDate)) {  // End date is not after end date, just return availabilty for end date
			dayCount=1;	// only check 1 date
		} else {
			dayCount=(int)(ChronoUnit.DAYS.between(startDate,endDate)+1);
		}
		System.out.println("Day count is ..."+dayCount);
		
		// We now create an array for all the slots
		boolean availableSlots[]=new boolean[(int)dayCount];
		endDate=endDate.plusDays(1);	// move to midnight after checkout
		// Now we try and load up the bookings for the dates we are interested in
		String hql = "FROM RoomBooking RB where RB.room.roomId='" + roomId + "' and RB.startDate>='"+startDate+"'  and  RB.endDate<='"+endDate+"'   ";
		Session session = DatabaseConnector.getFactory().openSession();
		Query <RoomBooking> query = session.createQuery(hql, RoomBooking.class);
		List<RoomBooking> results = query.list();
		for (int day=0;day<dayCount;day++) {		// Assume at first all are true
		    availableSlots[day]=true;
		}
		System.out.println("Results size is "+results.size());
		if (results.size() > 0) {
			
			// We have got some bookings, so we go through each date and calculate which days
			// to remove from available	
			   for (int idx=0;idx<results.size();idx++) {
				  RoomBooking rb=results.get(idx);
				  System.out.println("Got booking id is "+rb.getRoomBookingId());
				  // calcute offset into boolean array
				  int daysFromStart=(int)(ChronoUnit.DAYS.between(startDate,rb.getStartDate()));
				  int totalDays=(int)(ChronoUnit.DAYS.between(rb.getStartDate(),rb.getEndDate())+1);
				  System.out.println("Days start "+daysFromStart+"  day count is "+totalDays);
				  for (int day=0;day<totalDays;day++) {
					  availableSlots[day+daysFromStart]=false;
				  }
			   }
		} 
		return availableSlots;
	}
	
	/**
	 * See if this room is available for these dates
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public boolean isAvailable(LocalDate startDate,LocalDate endDate) {
		boolean [] availability=getAvailability(startDate,endDate);
		// Now see if all day's are available for the full range
		boolean ok=true;
		for (int idx=0;idx<availability.length;idx++) {
			if (!availability[idx]) {
				ok=false;
				break;
			}
		}
		System.out.println("Checking available for room "+this.roomNumber);
		return(ok);
	}
	
	
}
