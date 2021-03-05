package model;


public class RoomDescription {
	private int roomDescriptionId;
	private String description;
	private int maxOccupancy;
	private Money tarrif;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	public Money getTarrif() {
		return tarrif;
	}
	public void setTarrif(Money tarrif) {
		this.tarrif = tarrif;
	}
}
