package com.demichev.service;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demichev.abstractC.Profile;
import com.demichev.model.Person;
import com.demichev.model.Visit;
import com.demichev.hibernate.util.HibernateUtil;
import com.demichev.model.Client;


//Client Registration Service

public class RegisterService {
	
	
	//boolean method registration
public boolean register(Client user,Person p){
	 Session session = HibernateUtil.openSession(); //open new session
	 if(isUserExists(user)) return false;	//if there is such user say that we cannot register
	
	 Transaction tx = null;	//create transaction
	 try {
		 tx = session.getTransaction(); 
		 tx.begin();
		 session.saveOrUpdate(p); //save person to DB
		 session.saveOrUpdate(user); //save client to DB 
		 tx.commit(); //commit changes
	 } catch (Exception e) {
		 if (tx != null) {
			 tx.rollback(); //if was an error roll back all changes
		 }
		 e.printStackTrace();
	 } finally {
		 session.close(); //close session
	 }	
	 return true;
}

//checking is there such client aready
public boolean isUserExists(Profile user){
	 Session session = HibernateUtil.openSession(); //new session
	 boolean result = false; 
	 Transaction tx = null; //create transaction
	 try{
		 tx = session.getTransaction();
		 tx.begin();
		 Query query = session.createQuery("from Client where Id='"+user.getId()+"'"); //looking in DB for such Client (new query created)
		 Client u = (Client)query.uniqueResult(); // executing that query
		 tx.commit(); //commit changes
		 if(u!=null) result = true; //if there is no such client say false
	 }catch(Exception ex){
		 if(tx!=null){
			 tx.rollback(); //if there was problem, roll back all chenges
		 }
	 }finally{
		 session.close(); //close session
	 }
	 return result;
}

public boolean reserveVisit(Visit v) {
	 Session session = HibernateUtil.openSession(); //open new session

	
	 Transaction tx = null;	//create transaction
	 try {
		 tx = session.getTransaction(); 
		 tx.begin();
		 session.saveOrUpdate(v); //save person to DB
		 tx.commit(); //commit changes
	 } catch (Exception e) {
		 if (tx != null) {
			 tx.rollback(); //if was an error roll back all changes
		 }
		 e.printStackTrace();
	 } finally {
		 session.close(); //close session
	 }	
	 return true;
}

}