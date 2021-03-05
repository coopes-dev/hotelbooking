package model;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.google.common.hash.Hashing;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	public User(String emailAddress, String username, String password, Role role) {
		super();
		this.emailAddress = emailAddress;
		this.username = username;
		
		this.password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
		this.role = role;
	}
	
	public User() {
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int userId;
	private String emailAddress;
	private Date dateCreated;
	private String username;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

}
