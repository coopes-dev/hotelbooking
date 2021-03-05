package database.hibernate;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DatabaseConnector {
	private static SessionFactory factory;

	public static ConnectionStatus testConnection(Configuration hibernateConfig) {
		try {
			SessionFactory factory = hibernateConfig.buildSessionFactory();
		} catch (HibernateException ex) {
			
			System.err.println("Failed to create sessionFactory object." + ex.getMessage());
			return(new ConnectionStatus(false,ex.getCause().getMessage()));		// failed to get session factory..
		}
		return(new ConnectionStatus(true));
	}

	
	public static final SessionFactory getFactory() {		
		if (factory == null) {
			try {
				DatabaseConfig config=new DatabaseConfig();
				Configuration hibernateConfig=config.getHibernateConfig();
				factory = hibernateConfig.buildSessionFactory();
			} catch (Throwable ex) {
				System.err.println("Failed to create sessionFactory object." + ex.getMessage());
		//		throw new ExceptionInInitializerError(ex);
				DatabaseConfigDialog config=new DatabaseConfigDialog();
				
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
			
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			//e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void loadObject(Object object,Integer id) {
		Session session =getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			session.load(object, id);
			tx.commit();
			System.out.println("Status is "+session.getStatistics());
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			//e.printStackTrace();
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
			//e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
