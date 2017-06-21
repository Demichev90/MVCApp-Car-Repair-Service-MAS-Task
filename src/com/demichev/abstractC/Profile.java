package com.demichev.abstractC;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.demichev.model.Person;



//Profile class for overlapping

@Entity //Create table (mapping - saying hibernate that it is an entity)
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Profile {

	//Persistent *-1 for overlapping
	@ManyToOne
	private Person p;

	//Id is autogenereted by hibernate
	@Id @GeneratedValue
	private int id;

	private String mail; //Optional attribute

	private String password;

	private String login;


	//names Set for making login unique
	private static final Set<String> names = new HashSet<String>(); //we need set of names to have unique names (logins)

	//empty constructor for hibernate purpose
	public Profile() {

	}


	//constructor with no mail
	public Profile(Person p, String password, String login) {
		this.p = p;
		this.password = password;
		this.login = login;
	}

	//constructor with mail
	public Profile(Person p, String password, String mail, String login) {
		super();
		this.p = p;
		this.password = password;
		this.login = login;
	}


	/**
	 * @return the p
	 */
	public Person getP() {
		return p;
	}

	/**
	 * @param p the p to set
	 */
	public void setP(Person p) {
		this.p = p;
	}


	//id getter
	public long getId() {
		return id;
	}

	//mail getter
	public String getMail() {
		return mail;
	}

	//mail setter
	public void setMail(String mail) {
		this.mail = mail;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	//login setter (unique login to system)
	public void setLogin (String name) throws Exception
	{
		if (names.contains(name))
			throw new Exception();
		if (this.login != null)
			names.remove(this.login);
		names.add(name);
		this.login = name;         
	}

	/**
	 * @return the names  maybe someday we will need it))
	 */
	public static Set<String> getNames() {
		return names;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	//login getter
	public String getLogin() {
		return login;
	}


	/**
	 * @param id the id to set (actually unused)
	 */
	public void setId(int id) {
		this.id = id;
	}


	

}
