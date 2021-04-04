package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Contact implements Serializable {
	
	@Id
	String email;
	String firstname;
	String surname;
	
	public Contact(String email, String firstname, String surname) {
		
		this.email = email;
		this.firstname = firstname;
		this.surname = surname;
	}
	
	public Contact() {
		
	}

}
