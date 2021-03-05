package model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RoomDescription {
	public RoomDescription(String description, int maxOccupancy, model.Money money) {
		super();
		this.description = description;
		this.maxOccupancy = maxOccupancy;
		this.tarrif = money;
	}
	
	public RoomDescription() {
		
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)		
	private int roomDescriptionId;
	private String description;
	private int maxOccupancy;
	
	@Embedded
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
