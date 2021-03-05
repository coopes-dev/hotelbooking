package uk.ac.liv.comp220.hibernate;

import javax.persistence.EntityTransaction;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DatabaseConnector {
	private static SessionFactory factory;

	public static final SessionFactory getFactory() {

		if (factory == null) {
			try {
				factory = new Configuration().configure().buildSessionFactory();
			} catch (Throwable ex) {
				System.err.println("Failed to create sessionFactory object." + ex.getMessage());
				throw new ExceptionInInitializerError(ex);
			}
		}
		return (factory);
	}
	/**
	 * 
	 * @param o
	 */
	public static void updateObject(Object object) {
		Session session =getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			session.update(object);
			tx.commit();
			System.out.println("Status is "+session.getStatistics());
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	

	/**
	 * 
	 * @param o
	 */
	public static void saveObject(Object object) {
		Session session =getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			session.save(object);
			tx.commit();
			System.out.println("Status is "+session.getStatistics());
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
