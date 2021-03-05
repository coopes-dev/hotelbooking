package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "ADDRESS")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)		
	private int addressID;	
	@Size(min = 1,message="House name or number missing")
	private String houseNameOrNumber;	
	@Size(min = 1,message="Street name missing")
	private String addressLine1;	
	private String addressLine2;	
	private String addressLine3;
	@Size(min = 1,message="Postal town missing")
	private String postalTownCity;
	
	@Size(min = 1,message="Post code missing")
	private String postalOrZipCode;
	
	private String countryCode;
	
	// Multiple addresses are connected to the same Person, an address book model
	@ManyToOne
	private Person person;
	public int getAddressID() {
		return addressID;
	}
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	public String getHouseNameOrNumber() {
		return houseNameOrNumber;
	}
	public void setHouseNameOrNumber(String houseNameOrNumber) {
		this.houseNameOrNumber = houseNameOrNumber;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	public String getPostalTownCity() {
		return postalTownCity;
	}
	public void setPostalTownCity(String postalTownCity) {
		this.postalTownCity = postalTownCity;
	}
	public String getPostalOrZipCode() {
		return postalOrZipCode;
	}
	public void setPostalOrZipCode(String postalOrZipCode) {
		this.postalOrZipCode = postalOrZipCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
