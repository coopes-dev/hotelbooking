package uk.ac.liv.comp220.user;

public class Patient extends User {
	
	private int id=0;
	
	
	/**
	 * Default constructor
	 */
	public Patient() {
		
	}
	
	/**
	 * Constructs a new Patient
	 * @param username   Username for patient
	 */
	public Patient(String username) {
		super(Role.PATIENT,username);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
