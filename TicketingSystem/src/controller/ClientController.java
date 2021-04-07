package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.Address;
import domain.Client;
import exceptions.ValidationException;
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
		
		if(!emailCheck(email)) {
			throw new ValidationException("Invalid mail");
		}
		
		Address address = new Address(street, housenumber,city,country,postalcode );
		Client client = new Client(name,address);
		client.addPhoneNumber(telephoneNumber);
		client.addContact(email,firstname,surname);
		
		clientRepo.createClient(client);
	}
	
	public boolean emailCheck(String email) {
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		 
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public void updateClient(Client client) {
		 clientRepo.updateClient(client);;
	}
	
	public List<Client> getClients(){
		return clientRepo.getClients();		
	}
	
	public Client getClientById(int id) {
		return clientRepo.getClientById(id);
	}
	

}
