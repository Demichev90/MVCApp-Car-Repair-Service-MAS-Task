package com.demichev.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//Master Entity class


@Entity //Create table (mapping - saying hibernate that it is an entity)
public class Master extends Employee{
	
	
	//Constructor with a least 1 branch
	public Master(Person p, String mail, String password, String login, Branch branch, long salary) {
		super(p, mail, password, login, branch, salary);
	}


	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 4663992236465407912L;
	
	
	//Persistent *-1 One Intern can have a lot of masters at same time
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="intern_id",referencedColumnName="id",nullable=true,unique=true)
	Intern intern;
	
	
	enum Spec{Engine,Chassis,Diagnost,Body} //Enum specialization class
	
	@Enumerated(EnumType.ORDINAL) 
	Spec spec;
	


	//intern getter
	public Intern getIntern() {
		return intern;
	}

	//intern setter
	public void setIntern(Intern intern) {
		intern.setMaster(this);
	}
	
	
	//specialization setter
	public void setSpec(Spec s){
		this.spec = s;
	}
	
	//specialization getter
	public Spec getSpec(){
		return spec;
	}
	
	//salary getter
	public long getSalary(){
		return super.getSalary();
	}
	
	//Income getter
	public long getIncome(){
		return (long) (getSalary() * 1.5);
	}
	
	
	
	
}
