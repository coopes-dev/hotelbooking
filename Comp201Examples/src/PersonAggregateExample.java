
public class PersonAggregateExample {
	
	public PersonAggregateExample(String surname, String forename1, String forename2, DateOfBirth dateOfBirth,Address address) {
		super();
		this.surname = surname;
		this.forename1 = forename1;
		this.forename2 = forename2;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}
	
	// Return address associated with Person
	public Address getAddress() {
		return(address);
	}
	
	private String surname;
	private String forename1;
	private String forename2;
	private DateOfBirth dateOfBirth;
	private Address address;
	
}
