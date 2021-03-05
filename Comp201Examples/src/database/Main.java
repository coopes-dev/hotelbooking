package database;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import database.hibernate.DatabaseConnector;

public class Main {
	
	
	
	
	
	
	// Do some testing run's of the classes
	private void test1() {
		System.out.println("Testing database connections....");
		try {
			SessionFactory sessionFactory=DatabaseConnector.getFactory(); // try to connect to remote Database
			
			// describe PersonAggregateExample;  will show this table
			
			DateOfBirth dob=new DateOfBirth(1,1,1963);
			Address address1=new Address("L69 3BX","16","Ashton Street","","Liverpool","Merseyside","GBR");
			Address address2=new Address("PR9 3EF","32","Preston Road","","Southport","Merseyside","GBR");
			PersonMultipleAddress	person=new PersonMultipleAddress("Coope","Seb","",dob);
			person.addAddress(address1);
			person.addAddress(address2);
			DatabaseConnector.saveObject(person);
			DatabaseConnector.saveObject(address1);
			DatabaseConnector.saveObject(address2);
			System.out.println("Id for add 1 is "+address1.getId()+" id for add 2 is "+address2.getId());
						
			
		} catch (Exception exc) {
			// Failed to connect ...
			//exc.printStackTrace();
		}
		System.out.println("Terminating main thread...");
	}

	public static void main(String[] args) {
		Main main=new Main();
		main.test1();
	}

}
