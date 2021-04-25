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

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

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
	private final SimpleIntegerProperty id = new SimpleIntegerProperty();;
	private final SimpleStringProperty name = new SimpleStringProperty();;
	@OneToOne(cascade = CascadeType.ALL)
	private final SimpleObjectProperty<Address> address = new SimpleObjectProperty<Address>();
	@OneToMany(cascade = CascadeType.ALL)
	@ElementCollection
	private final SimpleListProperty<String> telephoneNumbers = new SimpleListProperty<String>();
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="clientID")
	private final SimpleListProperty<Contact> contacts = new SimpleListProperty<Contact>();
	private final SimpleObjectProperty<Date> dateCreated = new SimpleObjectProperty<Date>();
	
	public Client(String name,String email,String firstname, String surname, String telephoneNumber,
			String street,int housenumber, String city, String country,int postalcode) {
		
		setName(name);
		setAddress(street, housenumber,city,country,postalcode);	
		setDateCreated(new Date()); 		
		contacts.add(new Contact(email,firstname,surname));
		telephoneNumbers.add(telephoneNumber);
	}
	
	protected Client() {
		
	}
	


	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public StringProperty nameProperty() {
		return name;		
	}
	
	public int getId() {
		return id.get();
	}
			
	public SimpleIntegerProperty idProperty() {
		return id;		
	}
	
	public Address getAddress() {
		return address.get();
	}
	
	public void setAddress(String street,int housenumber, String city, String country,int postalcode) {
		this.address.set(new Address(street, housenumber,city,country,postalcode));
	}
	
	public ObjectProperty<Address> addressProperty() {
		return address;		
	}
	
	public ObservableList<String> getTelephoneNumbers() {
		return telephoneNumbers.get();
	}
	
	public void addTelephoneNumbers(String number) {
		this.telephoneNumbers.add(number);
	}
	
	public ListProperty telephoneNumberProperty() {
		return telephoneNumbers;		
	}
	
	public ObservableList<Contact> getContacts() {
		return contacts.get();
	}
	
	public void addContact(String email,String firstname, String surname) {
		this.contacts.add(new Contact(email,firstname,surname));
	}
	
	public SimpleListProperty<Contact> contactProperty() {
		return contacts;		
	}
	
	public Date getDateCreated() {
		return dateCreated.get();
	}
	
	public void setDateCreated(Date date) {
		this.dateCreated.set(date);
	}
	
	public SimpleObjectProperty<Date> dateCreatedProperty() {
		return dateCreated;		
	}
	
	



}