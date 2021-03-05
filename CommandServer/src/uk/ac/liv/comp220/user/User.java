package uk.ac.liv.comp220.user;

import java.util.Date;
import java.util.Random;

import uk.ac.liv.comp220.security.RC4;

public class User {
	
	public User() {
		
	}
	
	protected User(Role role, String username) {
		super();
		this.role = role;
		this.username = username;
	}
	
	private int id;						// row id
	private Role role;					// system role for user
	private String username;			// login username for user
	private String password;			// password for user
	private Date dateOfBirth;			// Date of birth for user
	private String forename1;
	private String forename2;
	private String surname;
	private long challenge=0;
	
	/**
	 * Checks password
	 * @param response  Challenge encrypted with password
	 * @return   True if response is correct for this user
	 */
	public boolean checkPassword(String response) {
		if (challenge!=0) {
			RC4 cipher=new RC4(password);
			String expectedResponse=cipher.encrypt(Long.toString(challenge));
			challenge=0;
			return(response.equals(expectedResponse));
		}
		return(false);
	}
	
	private int ctr=0;
	public void makeChallenge() {
		Random r=new Random(System.currentTimeMillis()+ctr++);
		challenge=r.nextLong();
		while (challenge==0) {
			challenge=r.nextLong();	
		}
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getForename1() {
		return forename1;
	}
	public void setForename1(String forename1) {
		this.forename1 = forename1;
	}
	public String getForename2() {
		return forename2;
	}
	public void setForename2(String forename2) {
		this.forename2 = forename2;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public long getChallenge() {
		return challenge;
	}

	public void setChallenge(long challenge) {
		this.challenge = challenge;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
