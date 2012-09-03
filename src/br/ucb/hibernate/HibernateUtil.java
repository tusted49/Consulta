package br.ucb.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void setSessionFactory(SessionFactory sessionFactory)	{
		HibernateUtil.sessionFactory = sessionFactory;
	}
	
	public static void shutdown() {
		getSessionFactory().close();
	}
	
}
