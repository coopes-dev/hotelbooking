package model;

import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name = "ADDRESS")
public class Address {
	
	private int addressID;	
	private String houseNameOrNumber;	
	private String addressLine1;	
	private String addressLine2;	
	private String addressLine3;	
	private String postalTownCity;	
	private String postalOrZipCode;
	private String countryCode;
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
