package domain;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import exceptions.ValidationException;

@Entity
public class Contact implements Serializable {
	
	@Id
	String email;
	String firstname;
	String surname;
	
	public Contact(String email, String firstname, String surname) {
		
		if(!emailCheck(email)) {
			throw new ValidationException("Invalid mail");
		}
		
		this.email = email;
		this.firstname = firstname;
		this.surname = surname;
	}
	
	public Contact() {
		
	}
	
	
	public boolean emailCheck(String email) {
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		 
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	@Override
	public String toString() {
		return this.firstname + " - " + this.surname + " - " + this.email;
	}
}
