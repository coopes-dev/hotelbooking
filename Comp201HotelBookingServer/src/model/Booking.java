package model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Booking {
	public Booking(Date dateOfBooking, Guest guest) {
		super();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(dateOfBooking);
		calendar.set(Calendar.HOUR,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		
		this.dateOfBooking = dateOfBooking;
		this.guest = guest;
	}
	
	public Booking() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)		
	private int bookingId;
	private Date dateOfBooking;
	
	// Many bookings to one guest ... each booking owned by only 1 guest
	@ManyToOne
	private Guest guest;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Date getDateOfBooking() {
		return dateOfBooking;
	}

	public void setDateOfBooking(Date dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}
}
