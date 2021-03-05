package model;

public class Guest extends Person {
	int guestID;
	String email;
	public int getGuestID() {
		return guestID;
	}
	public void setGuestID(int guestID) {
		this.guestID = guestID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
