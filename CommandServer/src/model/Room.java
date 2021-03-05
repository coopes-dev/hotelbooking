package model;

public class Room {
	
	public Room(int roomNumber, Hotel hotel, RoomDescription roomDescription) {
		super();
		this.roomNumber = roomNumber;
		this.hotel = hotel;
		this.roomDescription = roomDescription;
	}
	
	private int roomId;
	private int roomNumber;
	private Hotel hotel;
	private RoomDescription roomDescription;
}
