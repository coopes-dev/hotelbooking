package uk.ac.liv.comp220.commands;

import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.junit.runners.Parameterized.Parameters;

import java.util.Vector;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import database.hibernate.DatabaseConnector;
import model.Address;
import model.Guest;
import model.Person;
import model.Role;

public class AddGuestCommand extends AuthenticatedCommandBase {
	public static final String name="addGuest";

	@Override
	protected ServerResponse onAuthenticatedExecute() {
		ServerResponse response=new ServerResponse(ResponseCode.GUEST_ADDED_OK);
		String surname=this.arguments.get("surname");
		// Make new guest object
		Guest guest=new Guest();
		guest.setSurname(this.arguments.get("surname"));
		guest.setForename1(arguments.get("forename1"));
		guest.setForename2(arguments.get("forename2"));
		guest.setEmail(arguments.get("email"));
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Guest>> constraintViolations =validator.validate(guest );
    	HashMap <String,String> messages=new HashMap<String,String>();	// to store errors
        if (constraintViolations.size()>0) {
			Iterator<ConstraintViolation<Guest>> violations=constraintViolations.iterator();
			while (violations.hasNext()) {
				ConstraintViolation<Guest> violation=violations.next();
				messages.put(violation.getPropertyPath().toString(),violation.getMessage());
			}
			// the guest has not been validated...
		}
		// We will now see if guest is already there..
		Session session=DatabaseConnector.getFactory().openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Guest> cr = cb.createQuery(Guest.class);
		Root<Guest> root = cr.from(Guest.class);
		Predicate[] predicates = new Predicate[3];
		predicates[0] = cb.equal(root.get("surname"),arguments.get("surname"));
		predicates[1] = cb.equal(root.get("forename1"),arguments.get("forename1"));
		if (arguments.get("dateOfBirth")==null) {
			predicates[2] =cb.isNull(root.get("dateOfBirth"));
		} else 
		{
		   predicates[2] = cb.equal(root.get("dateOfBirth"),arguments.get("dateOfBirth"));
		}
		cr.select(root).where(predicates);
		Query<Guest> query = session.createQuery(cr);
		System.out.println("Quesry is "+query.getQueryString());
		List<Guest> results = query.getResultList();
		System.out.println("Quesry is "+query.getQueryString());
		
		if (results.size()>0) {
			// Guest already present
			response=new ServerResponse(ResponseCode.DUPLICATE_GUEST);
			return response;
		}
		// Now try and construct address
		Address address=new Address();
		address.setHouseNameOrNumber(arguments.get("houseNameOrNumber"));
		address.setAddressLine1(arguments.get("addressLine1"));
		address.setAddressLine2(arguments.get("addressLine2"));
		address.setAddressLine3(arguments.get("addressLine3"));
		address.setPostalTownCity(arguments.get("postalTownCity"));
		address.setPostalOrZipCode(arguments.get("postalOrZipCode"));
		factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Set<ConstraintViolation<Address>> constraintViolationsA =validator.validate(address );
		if (constraintViolationsA.size()>0) {
			Iterator<ConstraintViolation<Address>> violations=constraintViolationsA.iterator();
			while (violations.hasNext()) {
				ConstraintViolation<Address> violation=violations.next();
				messages.put(violation.getPropertyPath().toString(),violation.getMessage());
			}
			// the guest has not been validated...
		}
		if (messages.size()>0) {
			response=new ServerResponse(ResponseCode.INVALID_FIELDS);

			response.getResponseData().setData(messages); // send back errors
			return(response);
		}		
		DatabaseConnector.saveObject(guest);
		address.setPerson(guest);  // inwert guest into address object
		DatabaseConnector.saveObject(address);  // save address object
		return response;
	}

	@Override
	protected Role[] getRoles() {
		Role [] roles= {Role.RECEPTIONIST};
		return(roles);
	}
	
	
	
}
