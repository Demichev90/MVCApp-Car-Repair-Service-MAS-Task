package com.demichev.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.demichev.model.Person;
import com.demichev.model.Car.Brand;


//Manager Entity class


@Entity //telling hibernate that it is an entity (table in db)
public class Manager extends Master{

	public Manager(Person p, String mail, String password, String login, Branch branch, Branch bManage, long salary) {
		super(p, mail, password, login, branch, salary);
		this.branchManager = bManage;
		bManage.setManager(this);
	}


	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -8847830204315250581L; //for serialization


	//Persistent 1-1 1 manager per 1 branch
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="branch_mng")
	Branch branchManager;


	//Persistent 1-* 1 manager - many parts
	@OneToMany(targetEntity = Part.class, mappedBy = "manager", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	Set<Part> parts = new HashSet<Part>();



	//branch manage getter
	public Branch getBranchManager() {
		return branchManager;
	}

	//set branch manage
	public void setBranchManager(Branch branchManager) {
		this.branchManager = branchManager; //branch to manager
		branchManager.setManager(this); //manager to branch

	}

	//salary getter
	public long getSalary(){
		return super.getSalary();
	}

	//income getter
	public long getIncome(){
		return (long) (getSalary()* 1.6);
	}

	//salary change for employee
	public void changeSalary(Employee e, long sal) throws Exception{
		e.setSalary(sal);
	}

	//fire some worker
	public void fire(Employee e, Branch b){
		b.getWorkers().remove(e);
		e.removeBranch(b);
	}

	//add new car
	public void addCar(String model, Client owner, Brand b, String name){
		Car c = new Car(model, owner, b, name);	
		owner.addCar(c);
	}

	//order some parts
	public void orderParts(Part p, int quantity){
		int buff = p.getQuantity();
		buff=+ quantity;
		p.setQuantity(buff);

	}

	/**
	 * @return the parts
	 */
	public Set<Part> getParts() {
		return parts;
	}

	/**
	 * @param parts the parts to set
	 */
	public void setParts(Set<Part> parts) {
		this.parts = parts; //parts to manager
		//manager to parts
		for (Part p : parts) {
			p.setManager(this);
		}
	}

	//add part
	public void addPart(Part part) {
		this.parts.add(part);
		part.setManager(this);
	}




}
