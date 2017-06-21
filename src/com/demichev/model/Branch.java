package com.demichev.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//Branch model class

@Entity //Create table (mapping - saying hibernate that it is an entity)
@Table(name="Branch")  //Creating Branch table in database
public class Branch {
	@Id @GeneratedValue  //id for unique purpose
	private int id;
	private String name;


	@OneToOne(cascade = CascadeType.ALL)  //persistence 1 - 1 with manager (each branch must have at least one manager)
	@JoinColumn(name="manager_id") 
	private Manager manager;


	//Persistent *-* with employee creating new additional table
	@ManyToMany(cascade=CascadeType.ALL)  
	@JoinTable(name="branch_empl", joinColumns=@JoinColumn(name="empl_id"), inverseJoinColumns=@JoinColumn(name="branch_id"))
	private Set<Employee> workers = new HashSet<Employee>();

	//Persistent - 1-* Each branch can have a lot of visits
	@OneToMany(targetEntity = Visit.class, mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Visit> visits = new HashSet<Visit>();


	//Branch constructor
	public Branch(String name, Manager manager) {
		this.name = name;
		this.manager = manager; //manager to branch
		manager.setBranchManager(this); //branch to manager
	}

	//empty Branch constructor
	public Branch() {
		//manager.setBranchManager(this); //branch to manager
	}

	
	//branch name getter
	public String getName() {
		return name;
	}

	//Branch name setter
	public void setName(String name) {
		this.name = name;
	}

	//getting manager of branch
	public Manager getManager() {
		return manager;
	}

	//setting manager of branch
	public void setManager(Manager manager) {
		this.manager = manager; //manager to branch
		manager.setBranchManager(this); //branch to manager
	}

	//get all workers of branch
	public Set<Employee> getWorkers() {
		return workers;
	}

	//set workers of branch
	public void setWorkers(Set<Employee> workers) {
		this.workers = workers;//workers to branch

		//branch to employee
		for (Employee e : workers) {
			e.addBranch(this);
		}

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
		this.visits = visits; //visits to branch

		//branch to visits
		for (Visit v : visits) {
			v.setBranch(this);
		}

	}

	//add visit to branch (only to branch)
	public void addVisit(Visit v){
		visits.add(v);
	}

	//delete visit from branch (only from branch)
	public void removeVisit(Visit v){
		//TODO
		this.visits.remove(v);
	}

	//add worker to list
	public void addWorker(Employee e) {
		this.workers.add(e); //worker to branch
		e.addBranch(this); //branch to worker

	}

	//removing worker
	public void removeWorker(Employee employee) {
		this.workers.remove(employee); //remove from branch workers list
		employee.removeBranch(this); //remove from branches list in worker

	}
}
