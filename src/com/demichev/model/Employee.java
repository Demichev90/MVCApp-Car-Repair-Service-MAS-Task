package com.demichev.model;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import com.demichev.abstractC.Profile;
import com.demichev.model.Person;

//Employee profile for overlapping class

@Entity //Create table (mapping - saying hibernate that it is an entity)
public class Employee extends Profile {
	
	

	//Employee constructor with at least 1 branch
	public Employee(Person p, String mail, String password, String login, Branch branch, long salary) {
		super(p, mail, password, login);
		this.salary = salary;
		this.branches.add(branch);
		branch.addWorker(this);
	}
	


	//Persistence *-* with branch with creating additional table
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="workers") 
	private Set<Branch> branches = new HashSet<Branch>(); //Complex attribute

	
	private long salary; //Derrived attribute

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 8183848005785580723L; //for serialization

	
	
	//changing branch for worker
	public void removeBranch(Branch branch){
		this.branches.remove(branch);
		//TODO
		
	}
	
	//adding new branch
	public void addBranch(Branch branch){
		this.branches.add(branch); //branch to worker
		branch.addWorker(this); //worker to branch
	}



	//salary getter
	public long getSalary() {
		return (long) (salary * 1.1);
	}

	//salary setter derrived attr
	public void setSalary(long salary) throws Exception {
		if((this.salary != 0) && (salary < this.salary)){
			throw new Exception("The salary cannot be decreased!"); 
		}
		this.salary = (long) (salary * 1.1);
	}
	
	
	//income return (with bonuses)
	public long getIncome(){
		return (long) (getSalary() * 1.1);
	}

	/**
	 * @return the branches
	 */
	public Set<Branch> getBranches() {
		return branches;
	}

	/**
	 * @param branches the branches to set
	 */
	public void setBranches(Set<Branch> branches) {
		this.branches = branches; //branches to employee
		
		//employee to branch
				for (Branch b : branches) {
					b.addWorker(this);
				}
	}

	
	
}
