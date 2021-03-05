package model;

import java.time.LocalDate;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ConstraintViolation;

import javax.validation.ValidatorFactory;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(uniqueConstraints= @UniqueConstraint(columnNames = {"surname", "forename1","forename2","dateOfBirth"}))
public class Person  {
	
	public Person(String surname, String forename1, String forename2,LocalDate dateOfBirth) {
		super();
		this.surname = surname;
		this.forename1 = forename1;
		this.forename2 = forename2;
		this.dateOfBirth = dateOfBirth;
	}
	
	public Person(String surname, String forename1, String forename2,int day,int month,int year) {
		super();
		this.surname = surname;
		this.forename1 = forename1;
		this.forename2 = forename2;
		this.dateOfBirth = LocalDate.of(year, month, day);
	}
	
	

	public Person() {
		// TODO Auto-generated constructor stub
	}



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)		
	private int personID;
	@Size(min = 2,message="Surname too short must be at least 2 characters")
	private String surname;	
	@NotNull(message="Forename missing")
	@Size(min = 2,message="Forname too short must be at least 2 characters")
	private String forename1;	
	private String forename2;	
	private LocalDate dateOfBirth;
	
	
	
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
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
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

		
	
}
