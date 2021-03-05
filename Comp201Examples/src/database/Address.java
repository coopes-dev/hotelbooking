package database;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
	name = "Address", 
    uniqueConstraints = {@UniqueConstraint(columnNames = {"code", "addressLine1"})}
)
public class Address implements Cloneable {
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="person_id", nullable=false)
	private PersonMultipleAddress person;


	/**
	 * 
	 * @param postcode  		Postage code of address as string
	 * @param addressLine1		House name or number
	 * @param addressLine2		Street name
	 * @param addressLine3		Address line 3
	 * @param postTownCity		Post town or city
	 * @param countyRegion		County or region
	 * @param countryCode		Country code
	 */
	public Address(String pcode, String addressLine1, String addressLine2, String addressLine3,
			String postTownCity, String countyRegion, String countryCode) {
		super();
		this.postcode = new Postcode(pcode);	// construct postage code
		this.addressLine1 = addressLine1;		// house name or number
		this.addressLine2 = addressLine2;		// address line 2
		this.addressLine3 = addressLine3;		// address line 3
		this.postTownCity = postTownCity;		// postage town or city
		this.countyRegion = countyRegion;		// county or region
		this.countryCode = countryCode;			// ISO 3 alpha country code
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)	
	private int id;

	// Postage code
	@Embedded
	private Postcode postcode;
	// House name or number
	private String addressLine1;
	// Secondary address libne
	private String addressLine2;
	// Tertiary address line
	private String addressLine3;
	
	// Post town or City
	private String postTownCity;
	
	// County or region
	private String countyRegion;
	
	// ISO 3166-1 alpha-3 code
	private String countryCode;
	
	
	public Address clone() throws  CloneNotSupportedException 
	{ 
		Address address=(Address)super.clone();
		return(address);
	}


	public Postcode getPostcode() {
		return postcode;
	}


	public String getAddressLine1() {
		return addressLine1;
	}


	public String getAddressLine2() {
		return addressLine2;
	}


	public String getAddressLine3() {
		return addressLine3;
	}


	public String getPostTownCity() {
		return postTownCity;
	}


	public String getCountyRegion() {
		return countyRegion;
	}


	public String getCountryCode() {
		return countryCode;
	}


	public void setPostcode(Postcode postcode) {
		this.postcode = postcode;
	}


	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}


	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}


	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}


	public void setPostTownCity(String postTownCity) {
		this.postTownCity = postTownCity;
	}


	public void setCountyRegion(String countyRegion) {
		this.countyRegion = countyRegion;
	}


	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	} 
	
	// Checks for partial equality of postcode, addressLine1, addressLine2 and Country matches, it is assumed to match
	public boolean equals(Address other) {
		// If any fail to match then return false
		if (!other.postcode.getCode().equals(postcode.getCode())) {
			return(false);
		}
		if (!other.addressLine1.equals(addressLine1)) {
			return(false);
		}
		if (!other.addressLine2.equals(addressLine2)) {
			return(false);
		}
		if (!other.countryCode.equals(countryCode)) {
			return(false);
		}		
		// All must match so return true
		return(true);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public void setPerson(PersonMultipleAddress person) {
		this.person=person;
	}

}
