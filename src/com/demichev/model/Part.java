package com.demichev.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


//Part entity class


@Entity //Create table (mapping - saying hibernate that it is an entity)
@Table(name="Part")
public class Part extends Equipment {

	
	
	//Persistent *-1 one manager arrange a lot of parts
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="manager",referencedColumnName="id",nullable=false,unique=true)
	Manager manager;
	
	
	//Constructor for part
	public Part(int id, String name, int quantity) throws Exception {
		super(id, name, quantity);
	}

	//Use method
	public void use(){
		int q = this.getQuantity();
		q--;
		this.setQuantity(q);
	}


	/**
	 * @return the manager
	 */
	public Manager getManager() {
		return manager;
	}


	/**
	 * @param manager the manager to set
	 */
	public void setManager(Manager manager) {
		manager.addPart(this);
	}
	
	
	
}
