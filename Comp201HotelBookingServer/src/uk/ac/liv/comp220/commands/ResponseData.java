package uk.ac.liv.comp220.commands;

import model.Role;

public class ResponseData {
	private Role role;
	private Object data;
	private Object bookings;
	private Object roomBookings;	// list of room bookings
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getBookings() {
		return bookings;
	}

	public void setBookings(Object bookings) {
		this.bookings = bookings;
	}

	public Object getRoomBookings() {
		return roomBookings;
	}

	public void setRoomBookings(Object roomBookings) {
		this.roomBookings = roomBookings;
	}
	
	
}
