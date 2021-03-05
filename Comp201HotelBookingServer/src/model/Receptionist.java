package model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "receptionistID")
public class Receptionist extends User {
	
	public Receptionist(String emailAddress, String username, String password,Hotel hotel) {
		super(emailAddress, username, password,Role.RECEPTIONIST);
		this.hotel=hotel;
	}
	
	public Receptionist() {
		this.setRole(Role.RECEPTIONIST);
	}

	@ManyToOne
	private Hotel hotel;		// Which hotel is this receptionist working for

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	
}
