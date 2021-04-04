package controller;

import domain.Address;
import domain.Client;
import repository.ClientDao;
import repository.ClientDaoJpa;
import repository.TicketDao;

public class ClientController {
	
	private ClientDao clientRepo;
	
	
	public ClientController() {
		clientRepo = new ClientDaoJpa();
	}
	
	public void createClient(String street,int housenumber, String city, String country,
			int postalcode,String name,String telephoneNumber,String email, String firstname, String surname) {
		
		Address address = new Address(street, housenumber,city,country,postalcode );
		Client client = new Client(name,address);
		client.addPhoneNumber(telephoneNumber);
		client.addContact(email,firstname,surname);
		
		clientRepo.createClient(client);
	}
	
	
	
	

}
