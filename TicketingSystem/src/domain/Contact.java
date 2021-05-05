package domain;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import Helpers.EmailValidator;
import exceptions.ValidationException;

@Entity
public class Contact implements Serializable {
	
	@Id
	String email;
	String firstname;
	String surname;
	
	@ManyToOne
	@JoinColumn(name="clientId")
	Client client;
	
	
	
	public Contact(String email, String firstname, String surname) {
		
		if(!EmailValidator.emailCheck(email)) {
			throw new ValidationException("Invalid mail");
		}
		
		this.email = email;
		this.firstname = firstname;
		this.surname = surname;
	}
	
	public Contact() {
		
	}
	
	@Override
	public String toString() {
		return this.firstname + " - " + this.surname + " - " + this.email;
	}
}
