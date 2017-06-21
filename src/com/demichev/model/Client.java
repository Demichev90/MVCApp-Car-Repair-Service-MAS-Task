package com.demichev.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import com.demichev.abstractC.Profile;
import com.demichev.model.Person;
import com.demichev.model.Car.Brand;
import com.demichev.model.Visit.Timing;


//Client profile class (profile is for overlapping)

@Entity //Create table (mapping - saying hibernate that it is an entity)
public class Client extends Profile {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -3313502654601708943L; //for serialization
	


	//Persistent 1-* 1 Client can have a lot of visits
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Visit> visits = new HashSet<Visit>();  //Multi-valued attribute

	//Persistent 1-* One client can have a lot of cars
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Car> cars = new ArrayList<Car>();


	//empty constructor for registration purpose (hibernate)
	public Client() {
		super();
	}
	
	
	//Client constructor
	public Client(Person p, String mail, String password, String login) {
		super(p, password, mail, login);
	}

	//Removing car
	public void removeCar(Car car){
		cars.remove(car); //remove from clients list
		car.removeOwner(); //remove owner from car
	}

	//get all cars
	public List<Car> getCars() {
		return cars;
	}

	//add new car
	public void addCar(String model, Brand b, String name) {
		Car c = new Car(model, this, b, name); //create new car with this owner
		cars.add(c); //add owning car to this client
	}

	//add car that already exists
	public void addCar(Car c){
		cars.add(c); //car to client
		c.setOwner(this); //client to car
	}

//	//adding client to visit
//	public void addVisit(Visit v){
//		visits.add(v); //visit to client
//		v.setClient(this); //client to visit
//	}


	//get all Visits
	public Set<Visit> getAllVisits() {
		return visits;
	}

	//cancel Visit
	public void cancelVisit(Visit visit) {
		visits.remove(visit); //remove visit from client
		visit.getBranch().removeVisit(visit); //remove visit from branch
		visit.getCar().removeVisit(visit); //remove visit from car
	}



//	//arrange new visit
//	public Visit newVisit(Branch branch, Date date, Timing time, Car car, Client c) {
//		Visit v = new Visit(date, time, car, branch, this); //create visit
//		car.addVisit(v); //add visit to car
//		branch.addVisit(v); //add visit to branch
//		return v;
//
//	}



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
		this.visits = visits; //visits to client

	}


	/**
	 * @param cars the cars to set
	 */
	public void setCars(List<Car> cars) {
		this.cars = cars; //cars to client

		for (Car c : cars) { //client to cars
			c.setOwner(this);

		}
	}

	

}
