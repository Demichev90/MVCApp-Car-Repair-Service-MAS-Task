package com.demichev.model;

import javax.persistence.Entity;
import com.demichev.model.Person;


//Diagnostic entity class


@Entity //Create table (mapping - saying hibernate that it is an entity)
//tell hibernate to create diagnostic
public class Diagnostic extends Employee{



	public Diagnostic(Person p, String mail, String password, String login, Branch branch, long salary) {
		super(p, mail, password, login, branch, salary);
	}

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 6215459528090056487L; //for serialization

	//salary getter
	public long getSalary(){
		return super.getSalary();
	}

	//income getter
	public long getIncome(){
		return (long) (getSalary()* 1.3);
	}
	
	
	
}
