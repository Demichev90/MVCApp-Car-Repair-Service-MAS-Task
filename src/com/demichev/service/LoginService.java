package com.demichev.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demichev.abstractC.Profile;
import com.demichev.hibernate.util.HibernateUtil;
import com.demichev.model.Branch;
import com.demichev.model.Car;
import com.demichev.model.Client;
import com.demichev.model.Visit;


//CLient Login Service class

public class LoginService {

	//is all ok during auth? method
    public boolean authenticateUser(String userId, String password) {
        Client user = getUserByUserId(userId);   //find user by login
        if(user!=null && (user.getLogin().equals(userId)) && user.getPassword().equals(password)){ //check is it the same?
            return true;
        }else{ 
            return false;
        }
    }

    
    //find user by login method
    public Client getUserByUserId(String userId) { 
        Session session = HibernateUtil.openSession(); //opening new session
        Transaction tx = null; //create transaction
        Client user = null; //create Client
        try {
            tx = session.getTransaction(); //init transaction
            tx.begin(); //start transaction
            Query query = session.createQuery("from Profile where login='"+userId+"'"); //create query for looking in database
            user = (Client)query.uniqueResult(); //init client with 1 from database
            tx.commit(); //commit changes
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); //if was mistake rollback chenges
            }
            e.printStackTrace();
        } finally {
            session.close(); //close session
        }
        return user;
    }
    
    //List all clients method
    @SuppressWarnings("unchecked")
	public List<Client> getListOfUsers(){
        List<Client> list = new ArrayList<Client>();
        Session session = HibernateUtil.openSession(); //opening new session
        Transaction tx = null;        //create transaction
        try {
            tx = session.getTransaction(); //init transaction
            tx.begin(); //start transaction
            list = session.createQuery("from Profile").list();    //create and execute query for getting from database                
            tx.commit(); //commit changes
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); //if was mistake rollback chenges
            }
            e.printStackTrace();
        } finally {
            session.close(); //close session
        }
        return list;
    }
    
  //List all cars method
    @SuppressWarnings("unchecked")
	public List<Car> getListOfCars(Profile profile){
        List<Car> list = new ArrayList<Car>();
        Session session = HibernateUtil.openSession(); //opening new session
        Transaction tx = null;        //create transaction
        try {
            tx = session.getTransaction(); //init transaction
            tx.begin(); //start transaction
            list = session.createQuery("from Car where owner='"+profile.getId()+"'").list();    //create and execute query for getting from database 
            tx.commit(); //commit changes
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); //if was mistake rollback chenges
            }
            e.printStackTrace();
        } finally {
            session.close(); //close session
        }
        return list;
    }
    
    
    @SuppressWarnings("unchecked")
   	public List<Car> getListOfAllCars(){
           List<Car> list = new ArrayList<Car>();
           Session session = HibernateUtil.openSession(); //opening new session
           Transaction tx = null;        //create transaction
           try {
               tx = session.getTransaction(); //init transaction
               tx.begin(); //start transaction
               list = session.createQuery("from Car").list();    //create and execute query for getting from database 
               tx.commit(); //commit changes
           } catch (Exception e) {
               if (tx != null) {
                   tx.rollback(); //if was mistake rollback chenges
               }
               e.printStackTrace();
           } finally {
               session.close(); //close session
           }
           return list;
       }
    
    //List all visits method
    @SuppressWarnings("unchecked")
	public List<Visit> getListOfVisits(Profile profile){
        List<Visit> list = new ArrayList<Visit>();
        Session session = HibernateUtil.openSession(); //opening new session
        Transaction tx = null;        //create transaction
        try {
            tx = session.getTransaction(); //init transaction
            tx.begin(); //start transaction
            list = session.createQuery("from Visit where client_id='"+profile.getId()+"'").list();    //create and execute query for getting from database                
            
            tx.commit(); //commit changes
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); //if was mistake rollback chenges
            }
            e.printStackTrace();
        } finally {
            //session.close(); //close session
        }
        return list;
    }
    
    //List all visits method
    @SuppressWarnings("unchecked")
	public Branch getBranchByName(String uniqueName){
       Branch branch = new Branch();
        Session session = HibernateUtil.openSession(); //opening new session
        Transaction tx = null;        //create transaction
        try {
            tx = session.getTransaction(); //init transaction
            tx.begin(); //start transaction
            branch = (Branch) session.createQuery("from Branch where name='"+uniqueName+"'").uniqueResult();    //create and execute query for getting from database                
            tx.commit(); //commit changes
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); //if was mistake rollback chenges
            }
            e.printStackTrace();
        } finally {
            session.close(); //close session
        }
        return branch;
    }


	public List<Visit> getListOfAllVisits() {
		 List<Visit> list = new ArrayList<Visit>();
         Session session = HibernateUtil.openSession(); //opening new session
         Transaction tx = null;        //create transaction
         try {
             tx = session.getTransaction(); //init transaction
             tx.begin(); //start transaction
             list = session.createQuery("from Visit").list();    //create and execute query for getting from database 
             tx.commit(); //commit changes
         } catch (Exception e) {
             if (tx != null) {
                 tx.rollback(); //if was mistake rollback chenges
             }
             e.printStackTrace();
         } finally {
             session.close(); //close session
         }
         return list;
	}
    
}
