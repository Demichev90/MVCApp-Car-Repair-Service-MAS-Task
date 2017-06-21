package com.demichev.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import com.demichev.model.Person;


//Intern entity class


@Entity //Create table (mapping - saying hibernate that it is an entity)
public class Intern extends Employee {
	



	public Intern(Person p, String mail, String password, String login, Branch branch, long salary) {
		super(p, mail, password, login, branch, salary);

	}

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -3312766863899638072L; //for serialization
	Date startDate;
	Date endDate;
	
	
	//Persistence 1-* One intern can have a lot of masters at same time
	@OneToMany(targetEntity = Master.class, mappedBy = "intern", fetch = FetchType.EAGER)
	Set<Master> masters = new HashSet<Master>();
	

	//master setter
	public void setMaster(Master master){
		this.masters.add(master); //add master to intern
		master.setIntern(this); //intern to Master
	}


	//start date getter
	public Date getStartDate() {
		return startDate;
	}

	//start date setter
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	//end date getter
	public Date getEndDate() {
		return endDate;
	}

	//end date setter
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	//get all masters
	public Set<Master> getMasters() {
		return masters;
	}

	//set all masters
	public void setMasters(Set<Master> masters) {
		this.masters = masters; //master to intern
		
		//intern to masters
				for (Master m : masters) {
					m.setIntern(this);
				}
	}

	//salary getter
	public long getSalary(){
		return super.getSalary();
	}
	
	//income getter
	public long getIncome(){
		return (long) (getSalary() * 1.2);
	}
	
	
	
}
