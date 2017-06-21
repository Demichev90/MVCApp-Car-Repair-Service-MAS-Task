package com.demichev.model;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


@Entity //Create table (mapping - saying hibernate that it is an entity)
public class Staff extends Employee{

	
	public Staff(Person p, String mail, String password, String login, Branch branch, long salary) {
		super(p, mail, password, login, branch, salary);
		// TODO Auto-generated constructor stub
	}

	private String role;
	
	
	//Persistent 1-* One staff member can have a lot of instruments
	@OneToMany(targetEntity = Instrument.class, mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Instrument> instruments = new HashSet<Instrument>();
	
	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -4445600154279811249L;


	//salary getter
	public long getSalary(){
		return super.getSalary();
	}
	
	//income getter
	public long getIncome(){
		return (long) (getSalary()* 1.2);
	}
	
	//role getter
	public String getRole() {
		return role;
	}
	
	//role setter
	public void setRole(String role) {
		this.role = role;
	}
	
	//instruments ordering method
	public void orderInstruments(Instrument i, int quantity){
		int buff = i.getQuantity();
		buff=+ quantity;
		i.setQuantity(buff);
		this.instruments.add(i);
		i.setStaff(this);
	}
	
	//adding instrument to staff ant staff to instr
	public void addInstrument(Instrument instr){
		this.instruments.add(instr);
		instr.setStaff(this);
	}

	/**
	 * @return the instruments
	 */
	public Set<Instrument> getInstruments() {
		return instruments;
	}

	/**
	 * @param instruments the instruments to set
	 */
	public void setInstruments(Set<Instrument> instruments) {
		this.instruments = instruments;
		
		//staff to instruments
				for (Instrument i : instruments) {
					i.setStaff(this);
				}
	}
	
	
	
	
}
