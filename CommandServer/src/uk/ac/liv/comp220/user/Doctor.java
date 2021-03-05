package uk.ac.liv.comp220.user;

public class Doctor extends User {
	private int id=0;
	public Doctor() {
		
	}

	public Doctor(String username) {
		super(Role.DOCTOR, username);
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
