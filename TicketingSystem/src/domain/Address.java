package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Address implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	int id;
	String street;
	int housenumber;
	String city;
	String country;
	int postalcode;

	
	public Address(String street, int housenumber, String city, String country, int postalcode) {		
		this.street = street;
		this.housenumber = housenumber;
		this.city = city;
		this.country = country;
		this.postalcode = postalcode;
	}
	
	public Address() {
		
	}

	@Override
	public String toString() {
		return String.format("%s %d, %d %s, %s", this.street,this.housenumber,this.postalcode,this.city,this.country);
	}
	
	
	
}
