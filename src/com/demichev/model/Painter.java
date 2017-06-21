package com.demichev.model;

import javax.persistence.Entity;


//Painter Entity class


@Entity //Create table (mapping - saying hibernate that it is an entity)
public class Painter extends Employee {

	
	//Painter constructor
	public Painter(Person p, String mail, String password, String login, Branch branch, long salary) {
		super(p, mail, password, login, branch, salary);
	}

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 3591024898241291124L;


	//salary getter
	public long getSalary(){
		return super.getSalary();
	}
	
	//income getter
	public long getIncome(){
		return (long) (getSalary()* 1.2);
	}
	
	
	
}
