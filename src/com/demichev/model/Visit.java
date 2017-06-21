package com.demichev.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


//Visit entity class

@Entity //Create table (mapping - saying hibernate that it is an entity)
@Table(name="Visit")

public class Visit {
	@Id @GeneratedValue
	private int id;
	private Date date;

	//Enum class Timing (for managing time of visit)
	public enum Timing{morning,lunch,afternoon,evening};

	//Telling hibernate about enum class here
	@Enumerated(EnumType.ORDINAL) 
	private Timing timing;

	//PErsistent *-1 Many visits possible for 1 car
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="car",referencedColumnName="id",nullable=false,unique=false)
	private Car car;

	//Persistent *-1 Many visits possible to 1 branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="branchVisit",referencedColumnName="id",nullable=false,unique=false)
	private Branch branch;

	//Persistent *-1 Many visits possible for each client
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
	private Client client;


	//Visit constructor
	public Visit(Date date, Timing timing, Car car, Branch branch, Client client) {
		this.date = date;
		this.timing = timing;
		this.car = car;
		this.branch = branch;
		this.client = client;
	}
	
	public Visit(){
		
	}
	
	//Visit constructor
		public Visit(Date date,  Car car, Branch branch, Client client) {
			this.date = date;
			this.car = car;	
			this.branch = branch;
			this.client = client;
		}
	
	

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}


	/**
	 * @return the timing
	 */
	public Timing getTiming() {
		return timing;
	}


	/**
	 * @param timing the timing to set
	 */
	public void setTiming(Timing timing) {
		this.timing = timing;
	}


	/**
	 * @return the car
	 */
	public Car getCar() {
		return car;
	}


	/**
	 * @param car the car to set
	 */
	public void setCar(Car car) {
		this.car = car;
	}


	/**
	 * @return the branch
	 */
	public Branch getBranch() {
		return branch;
	}


	/**
	 * @param branch the branch to set
	 */
	public void setBranch(Branch branch) {
		this.branch = branch;
	}


	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}


	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
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

	
	//Visit remove
	public void removeVisit(){
		car.getVisits().remove(this);
		branch.getVisits().remove(this);
		client.getVisits().remove(this);
	}


}
