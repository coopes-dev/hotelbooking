package database;
import java.util.HashMap;
import java.util.Vector;

/**
 * 
 * @author coopes
 * This version uses aggregation and does not enforce thread safety
 * For many project this version is perfectly acceptable
 * Use one instance of this class per Thread, do not share instances
 * 
 */
public class PersonMultipleAddressSimpleVersion {

	private Vector<Address> addressBook = new Vector<Address>();
	
	public PersonMultipleAddressSimpleVersion(String surname, String forename1, String forename2, DateOfBirth dateOfBirth) {
		super();
		this.surname = surname;
		this.forename1 = forename1;
		this.forename2 = forename2;
		this.dateOfBirth = dateOfBirth;
	}

	public void addAddress(Address address) {
		addressBook.add(address);
	}

	public void deleteAddress(Address address) {
		Vector<Address> removeList = new Vector<Address>();
			addressBook.forEach((addressInList) -> {
				if (address.equals(addressInList)) {
					removeList.add(addressInList);
				}
			});
		addressBook.removeAll(removeList);
	}

	private String surname;
	private String forename1;
	private String forename2;
	private DateOfBirth dateOfBirth;


	public Vector<Address> getAddressBook() {
		return addressBook;
	}
	
	public boolean equals(PersonMultipleAddressSimpleVersion person) {
		if (!surname.equals(person.surname)) {
			return(false);
		}
		if (!forename1.equals(person.forename1)) {
			return(false);
		}
		if (!dateOfBirth.equals(person.dateOfBirth)) {
			return(false);
		}
		return(true);
	}

}
