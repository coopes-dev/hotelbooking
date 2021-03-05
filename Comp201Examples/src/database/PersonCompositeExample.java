package database;

public class PersonCompositeExample {
	
	
	public PersonCompositeExample(String surname, String forename1, String forename2, DateOfBirth dateOfBirth,Address address) {
		super();
		this.surname = surname;
		this.forename1 = forename1;
		this.forename2 = forename2;
		this.dateOfBirth = dateOfBirth;
		try {
			// using clone, stops reference being used to alter address
			// contained with person
			this.address = address.clone();				
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	private String surname;
	private String forename1;
	private String forename2;
	private DateOfBirth dateOfBirth;
	private Address address;
	
	public Address getAddress() {
		try {
			return address.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(null);	// failed to clone
	}
	
	/*
	 * Set's the postcode for the user
	 */
	public void setPostcode(String code) {
		Postcode pcode=new Postcode(code);
		address.setPostcode(pcode); // set the postcode for the user
	}
	
	/**
	 * 
	 * @param townCity Post town or city
	 */
	public void setPostTownCity(String townCity) {
		address.setPostTownCity(townCity);
	}
}
