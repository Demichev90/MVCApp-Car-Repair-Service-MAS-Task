package com.demichev.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


//Instrument entity class


@Entity //Create table (mapping - saying hibernate that it is an entity)
@Table(name="Instrument")  //Telling hibernate to create Instrument table in DB
public class Instrument extends Equipment {

	
	//Peresistent *-1 means that 1 staff member may have a lot of instruments
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="staff",referencedColumnName="id",nullable=true,unique=true)
	private Staff staff;
	
	
	//Constructor for Instrument
	public Instrument(int id, String name, int quantity) throws Exception {
		super(id, name, quantity);
		
	}


	//setting instrument to staff
	public void setStaff(Staff staff){
		this.staff = staff; //staff to instrument
		staff.addInstrument(this); //instrument to staff
	}


	/**
	 * @return the staff
	 */
	public Staff getStaff() {
		return staff;
	}

	
	
	
}
