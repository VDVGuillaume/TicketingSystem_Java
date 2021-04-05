package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Clients")
public class Client implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	public String name;
	@OneToOne(cascade = CascadeType.PERSIST)
	public Address address;
	@OneToMany
	@ElementCollection
	ArrayList<String> telephoneNumbers;
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name="clientID")
	ArrayList<Contact> contacts;
	public Date dateCreated;
	
	public Client(String name,Address adress) {
		
		this.name = name;
		this.address= adress;	
		contacts = new ArrayList<Contact>();
		telephoneNumbers = new ArrayList<String>();
		dateCreated = new Date();
	}
	
	protected Client() {
		
	}
	
	public void addPhoneNumber(String telephoneNumber){
		this.telephoneNumbers.add(telephoneNumber);
	}
	
	public void addContact(String email, String firstname, String surname) {
		contacts.add(new Contact(email,firstname,surname));
		
	}
	
	
}
