package database;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author coopes
 */

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(
		name = "Person", 
	    uniqueConstraints = {@UniqueConstraint(columnNames = {"surname","forename1", "dateOfBirth"})}
)
public class PersonMultipleAddress {

	public PersonMultipleAddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	@OneToMany(mappedBy="person")
	private Set<Address> addressBook = new HashSet<Address>();
	
	@Transient
	private HashMap <Long,Boolean> updated=new HashMap<Long,Boolean>();
	@Transient
	private HashMap <Long,Vector<Address>> copies=new HashMap<Long,Vector<Address>>();
	
	public PersonMultipleAddress(String surname, String forename1, String forename2, DateOfBirth dateOfBirth) {
		super();
		this.surname = surname;
		this.forename1 = forename1;
		this.forename2 = forename2;
		this.dateOfBirth = dateOfBirth;
	}

	public void addAddress(Address address) {
		address.setPerson(this);
		synchronized (addressBook) {
			try {
				addressBook.add(address.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updated.put(Thread.currentThread().getId(),false);		// forces update to make a new clone
		}
	}

	public void deleteAddress(Address address) {
		Vector<Address> removeList = new Vector<Address>();
		synchronized (addressBook) {
			addressBook.forEach((addressInList) -> {
				if (address.equals(addressInList)) {
					removeList.add(addressInList);
				}
			});
		}
		addressBook.removeAll(removeList);
		updated.put(Thread.currentThread().getId(),false);		// forces update to make a new clone
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)	
	private int person_id;
	private String surname;
	private String forename1;
	private String forename2;
	private DateOfBirth dateOfBirth;

	private void update() {
		if (updated.get(Thread.currentThread().getId())) {		// forces update to make a new clone
			return;
		}
		Vector<Address> addressBookDeepCopy = new Vector<Address>(); // make Vector to store the copies in		
		synchronized (addressBook) {
			addressBook.forEach((addressInList) -> {
				try {
					addressBookDeepCopy.add(addressInList.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		copies.put(Thread.currentThread().getId(),addressBookDeepCopy);
		updated.put(Thread.currentThread().getId(),false);		// forces update to make a new clone		
	}

	public Set<Address> getAddressBook() {
		return(addressBook);
		//update();
		//return copies.get(Thread.currentThread().getId());
	}
	
	public boolean equals(PersonMultipleAddress person) {
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
