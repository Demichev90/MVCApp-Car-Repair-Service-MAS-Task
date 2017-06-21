package com.demichev.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//Equipment entity class


@MappedSuperclass //Tell hibernate do not create table Equipment in database but use it as superclass
public class Equipment {
	@Id @GeneratedValue //ID auto generated for uniqueness
	private int id;
	private String name;
	private int quantity;


	//For unique names of Equipment
	private static final Set<String> names = new HashSet<String>(); 



	//Constructor for creating some equipment
	public Equipment(int id, String name, int quantity) throws Exception {
		this.id = id;
		this.setName(name); //using method for making name unique
		this.quantity = quantity;
	}


	//setting unique name
	public void setName (String name) throws Exception
	{
		if (names.contains(name))
			throw new Exception();
		if (this.name != null)
			names.remove(this.name);
		names.add(name);
		this.name = name;         
	}

	//name getter
	public String getName(){
		return this.name;
	}

	//quant getter
	public int getQuantity() {
		return quantity;
	}

	//quant setter
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set not used
	 */
	public void setId(int id) {
		this.id = id;
	}



}
