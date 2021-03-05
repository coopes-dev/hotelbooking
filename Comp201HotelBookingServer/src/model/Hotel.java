package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hotel {
	public Hotel() {
		
	}
	
	
	
	public Hotel(String name) {
		super();
		this.name = name;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int hotelId;
	
	
	@Column(unique = true)
	private String name;
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
