package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.Address;
import domain.Client;
import exceptions.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import repository.ClientDao;
import repository.ClientDaoJpa;
import repository.TicketDao;

public class ClientController {
	
	private ClientDao clientRepo;
	private ObservableList<Client> clients;
	
	
	public ClientController() {
		clientRepo = new ClientDaoJpa();
		clients =FXCollections.observableArrayList(
				clientRepo.getClients());
	}
	
	public void createClient(String name,String telephoneNumber,String email, 
			String firstname, String surname,String street,int housenumber, String city, String country,int postalcode) {
		
		Client client = new Client(name,email,firstname,surname, telephoneNumber, street, housenumber, city, country, postalcode);
		clientRepo.createClient(client);
		clients.add(client);
		
	}
	
	public ObservableList<Client> getClients() {
	    return FXCollections.unmodifiableObservableList(clients);	
	}
		
	
	public void updateClient(Client client) {
		
		clientRepo.updateClient(client);
	}
	
	
	public Client getClientById(int id) {
		return clientRepo.getClientById(id);
	}
	
	public void addObserver(ListChangeListener<Client> listener){
        clients.addListener(listener);
	}
    

}
