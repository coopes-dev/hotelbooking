package uk.ac.liv.comp220.user;

import org.hibernate.query.Query;

import java.util.List;

import org.hibernate.Session;

import uk.ac.liv.comp220.hibernate.DatabaseConnector;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Patient p=new Patient("duresk@liv.ac.uk");
		p.setPassword("password4321");
		DatabaseConnector.saveObject(p);
		System.out.println("Id for patient is "+p.getId());
		Session session =DatabaseConnector.getFactory().openSession();
		String username="sebby";
		String hql = "FROM User U where U.username='"+username+"'";
		Query <User> query = session.createQuery(hql,User.class);
		List <User> results = query.list();
		System.out.println("size of list is "+results.size());
		if (results.size()>0) {
			User user=results.get(0);
			System.out.println("Password is "+user.getPassword());
			System.out.println("Role is "+user.getRole()+" role id is "+user.getRole().ordinal());
			
		}

	}

}
