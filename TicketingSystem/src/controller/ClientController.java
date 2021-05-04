package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.Address;
import domain.Client;
import domain.Contact;
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
	
	public void createClient(Client client) {
		clientRepo.createClient(client);
		clients.add(client);
	}
	
	public ObservableList<Client> getClients() {
	    return FXCollections.unmodifiableObservableList(clients);	
	}
		
	public void updateClient(Client client) {
		
		clientRepo.updateClient(client);
		clients.set(clients.indexOf(client), client);
	}
	
	public Client getClientById(int id) {
		return clientRepo.getClientById(id);
	}
	
	public void addObserver(ListChangeListener<Client> listener){
        clients.addListener(listener);
	}
}
