package model;



import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class RoomBooking {
	public static int CHECK_IN_TIME=15;	// check in time in hours
	public static int CHECK_OUT_TIME=11;	// check out time in hours
	
	public RoomBooking(Booking booking, Room room, LocalDateTime startDate, LocalDateTime endDate) {
		super();
		this.booking = booking;
		this.room = room;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public RoomBooking(Booking booking, Room room, int startDateD,int startDateM,int startDateY, int endDateD,int endDateM,int endDateY) {
		super();
		this.booking = booking;
		this.room = room;
		this.startDate = LocalDateTime.of(startDateY,startDateM,startDateD,CHECK_IN_TIME,0);		
		this.endDate = LocalDateTime.of(endDateY,endDateM,endDateD,CHECK_OUT_TIME,0);
	}
	

	public RoomBooking() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)		
	private int roomBookingId;
	
	@ManyToOne
	private Booking booking;	// booking this room booking belongs to
	
	@OneToOne
	private Room room;			// room this room booking is associated with
	
	private LocalDateTime startDate;		// start of stay
	
	private LocalDateTime endDate;		// end of stay

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public int getRoomBookingId() {
		return roomBookingId;
	}

	public void setRoomBookingId(int roomBookingId) {
		this.roomBookingId = roomBookingId;
	}

	

}
