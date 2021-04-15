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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NamedQueries({
	
	@NamedQuery(
		    name="Client.getAll",
		    query="SELECT c FROM Client c"
		),	
	
	@NamedQuery(
		    name="Client.getById",
		    query="SELECT c FROM Client c WHERE c.id = :id"
		)	
	
	
})
@Table(name="Clients")
public class Client implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	@OneToOne(cascade = CascadeType.ALL)
	Address address;
	@OneToMany(cascade = CascadeType.ALL)
	@ElementCollection
	ArrayList<String> telephoneNumbers;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="clientID")
	ArrayList<Contact> contacts;
	Date dateCreated;
	
	public Client(String name,Address address) {
		
		setName(name);
		setAddress(address);	
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

	public String getName() {
		return name;
	}

	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
}
