package com.demichev.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


//Utils for hibernate
public class HibernateUtil {

	
	//creating session factory
    private static final SessionFactory sessionFactory;

    static {
        try {
        	//trying to init session factory with config from file
            sessionFactory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory(); 
            
            
        } catch (Throwable ex) {
        	//throw error if session factory was not created
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    //method for session open
    public static Session openSession() {
        return sessionFactory.openSession();
    }
}