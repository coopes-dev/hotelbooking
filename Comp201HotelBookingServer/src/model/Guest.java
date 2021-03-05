package model;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@PrimaryKeyJoinColumn(name = "guestID")
public class Guest extends Person {
	
	public Guest(String surname, String forename1, String forename2, int day, int month, int year) {
		super(surname, forename1, forename2, day, month, year);
		// TODO Auto-generated constructor stub
	}
	
	@Size(min = 1,message="Email missing")
	private String email;
	
	public Guest() {
		super();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public static void main(String argv[]) {
		Validator validator;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Guest p=new Guest();
        p.setSurname("1");
        p.setDateOfBirth(LocalDate.of(2020,8,23));
        p.setEmail("fsfw");
		Set<ConstraintViolation<Person>> constraintViolations =validator.validate(p );
		if (constraintViolations.size()>0) {
			Iterator<ConstraintViolation<Person>> violations=constraintViolations.iterator();
			while (violations.hasNext()) {
				ConstraintViolation<Person> violation=violations.next();
				System.out.println(violation.getMessage());
			}
		}
		
	}

}
