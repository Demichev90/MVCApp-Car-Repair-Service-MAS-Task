package com.demichev.service;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.demichev.hibernate.util.HibernateUtil;
import com.demichev.model.Car;
import com.demichev.model.Client;
import com.demichev.model.Visit;


//Car Adding Service Service

public class AddCarService {


	//boolean method registration
	public boolean register(Car c){
		Session session = HibernateUtil.openSession(); //open new session

		Transaction tx = null;	//create transaction
		try {
			tx = session.getTransaction(); 
			tx.begin();
			session.saveOrUpdate(c); //save car to DB
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


	//delete car
	public boolean delete(String carId) {
		
		 LoginService loginService = new LoginService();
		 List<Car> list = loginService.getListOfAllCars();
		 Car car = null;
		 for (Car c : list) {
			 if(c.getId() == Integer.parseInt(carId)){
				 car = c;	 
			 }
		 }

		Session session = HibernateUtil.openSession(); //open new session

		Transaction tx = null;	//create transaction
		try {
			tx = session.getTransaction(); 
			tx.begin();
			session.delete(car); //delete car from DB
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


	public boolean deleteVisit(String visitId) {
		 LoginService loginService = new LoginService();
		 List<Visit> list = loginService.getListOfAllVisits();
		 Visit visit = null;
		 for (Visit v : list) {
			 if(v.getId() == Integer.parseInt(visitId)){
				 visit = v;	 
			 }
		 }

		Session session = HibernateUtil.openSession(); //open new session

		Transaction tx = null;	//create transaction
		try {
			tx = session.getTransaction(); 
			tx.begin();
			session.delete(visit); //delete visit from DB
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