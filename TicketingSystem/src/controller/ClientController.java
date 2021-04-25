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
	
	public void createClient(String name,String telephoneNumber,String email, 
			String firstname, String surname,String street,int housenumber, String city, String country,int postalcode) {
		
		Client client = new Client(name,email,firstname,surname, telephoneNumber, street, housenumber, city, country, postalcode);
		clientRepo.createClient(client);
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
