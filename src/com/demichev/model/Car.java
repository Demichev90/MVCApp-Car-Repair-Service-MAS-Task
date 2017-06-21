package com.demichev.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.demichev.service.RegisterService;

//Car entity model

@Entity //Create table (mapping - saying hibernate that it is an entity)
@Table(name="Car") //creating car table in database (telling hibernate to do it)

public class Car {

	@Id @GeneratedValue //primary key is ID unique
	private int id;
	public enum Brand{BMW, Audi, VW, Mercedes, Saab} //enum class with brands

	private String name;
	private String model;

	
	
	//For unique names of Car
		private static final Set<String> names = new HashSet<String>(); 
	
	//Persistence *-1 owner can have a lot of cars
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
	private Client owner;

	//mapping enum class for hibernate
	@Enumerated(EnumType.ORDINAL)  
	private Brand brand;

	//Peristence 1-* One car can have a lot of visits
	@OneToMany(targetEntity = Visit.class, mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
	private Set<Visit> visits = new HashSet<Visit>();


	//Empty Constructor for creating car
		public Car() {
		}
	

	//Constructor for creating car
	public Car(String model, Client owner, Brand brand, String name) {
		this.model = model;
		try {
			this.setName(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.owner = owner; //car to owner
		owner.addCar(this); //owner to car
		this.brand = brand;
	}

	
	//Constructor for creating car
	public Car(String model, Client owner, String name) {
		this.model = model;
		try {
			this.setName(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.owner = owner; //car to owner
		owner.addCar(this); //owner to car
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
	
	

	//model getter
	public String getModel() {
		return model;
	}
	//model setter
	public void setModel(String model) {
		this.model = model;
	}

	//owner getter
	public Client getOwner() {
		return owner;
	}

	//owner setter
	public void setOwner(Client owner) {
		//remove previous owner
		Client c = this.getOwner();
		c.removeCar(this);
		//setting new owner
		this.owner = owner; //car to owner
	}
	

	//brand setter
	public void setBrand(Brand b){
		this.brand = b;
	}

	//brand getter
	public Brand getBrand(){
		return brand;
	}


	/**
	 * @return the visits
	 */
	public Set<Visit> getVisits() {
		return visits;
	}


	/**
	 * @param visits the visits to set
	 */
	public void setVisits(Set<Visit> visits) {
		this.visits = visits; // visit to car

		


	}

	//removing owner of car (only owner from car)
	public void removeOwner(){
		this.owner = null;
		
	}

	//removing visit (only visit from car)
	public void removeVisit(Visit v){
		//TODO
		this.visits.remove(v);
	}


	/**
	 * @return the id 
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set unused
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
}
