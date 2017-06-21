package com.demichev.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import com.demichev.abstractC.Profile;

//PErson model entity class

@Entity //Create table (mapping - saying hibernate that it is an entity)
@Inheritance(strategy= InheritanceType.JOINED)  // 
public class Person implements Serializable{
	
	private static final long serialVersionUID = 8418981121593003975L; //Auto genereted serial number for serialization
	
	@Id @GeneratedValue
	private long id;  //id parameter for search and unique purpose
	private String name;
	private String surname;
	private int phone;
	
	@Enumerated(EnumType.ORDINAL)  //mapping enum class for hibernate
	private Gender gender;
	
	
	//Persistent 1-* for overlapping
	@OneToMany(cascade = CascadeType.ALL)
	Set<Profile> profiles = new TreeSet<Profile>();
	

	//empty constructor for hibernate purpose (with extent)
	public Person(){
		addPerson(this);
	}
	
	/**
	 * @return the profiles
	 */
	public Set<Profile> getProfiles() {
		return profiles;
	}

	/**
	 * @param profiles the profiles to set
	 */
	public void setProfiles(Set<Profile> profiles) {
		this.profiles = profiles;
		
		//persons to profiles
				for (Profile p : profiles) {
					p.setP(this);
				}
	}

	//Enum class
	public enum Gender{ 
		male, female;
		}
	
	//simple constructor
	public Person(String name, String surname, int phone){ 
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		addPerson(this);  //adding person to extent
	}
	
	//gender setter
	public void setGender(Gender g){
		this.gender = g;
	}
	
	//we dont know income of some person
	public long getIncome(){
		return 0;
	}
	
	//gender getter
	public Gender getGender(){
		return gender;
	}
	
	//name getter
	public String getName() {
		return name;
	}

	

	//surname getter
	public String getSurname() {
		return surname;
	}

	//surname setter
	public void setSurname(String surname) {
		this.surname = surname;
	}


	//phone getter
	public int getPhone() {
		return phone;
	}

	//phone setter
	public void setPhone(int phone) {
		this.phone = phone;
	}


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	// EXTENT //Vector for extent (all persons)
	private static Vector<Person> extentPerson = new Vector<Person>(); 
	
	
	//add person to extent
	private static void addPerson(Person person) {
		extentPerson.add(person);
	}

	//remove some person from extent
	@SuppressWarnings("unused")
	private static void removePerson(Person person) {
		extentPerson.remove(person);
	}

	//method wich shows all extented persons
	public static void showExtent() {
		System.out.println("Person class extented:\n");
		for (Person person : extentPerson) {
			System.out.println(person);
		}
	}

	//Additional 2 methods for writing and reading extent from as example files, at this poit not used
	public static void writeExtent(ObjectOutputStream stream) throws IOException {
		stream.writeObject(extentPerson);
	}

	@SuppressWarnings("unchecked")
	public static void readExtent(ObjectInputStream stream) throws IOException {
		try {
			extentPerson = (Vector<Person>) stream.readObject();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}	
	
	/**
	 * @return the extentPerson
	 */
	public static Vector<Person> getExtentPerson() {
		return extentPerson;
	}

	/**
	 * @param extentPerson the extentPerson to set
	 */
	public static void setExtentPerson(Vector<Person> extentPerson) {
		Person.extentPerson = extentPerson;
	}
	
	
	
}
