package controller;

import domain.ApplicationUser;
import domain.Client;
import domain.Contract;
import domain.Ticket;
import domain.TicketType;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import repository.TicketDao;
import repository.TicketDaoJpa;

public class TicketController {
	
	private TicketDao ticketRepo;
	private ObservableList<Ticket> tickets;
	
	public TicketController() {		
		ticketRepo = new TicketDaoJpa();
		tickets = FXCollections.observableArrayList(
				ticketRepo.getTickets());
				
	}

	public void createTicket(String title, String description, TicketType type, Client client, 
			Contract contract, ApplicationUser assignedEngineer) {
		Ticket ticket = new Ticket(title,description,type,client,contract,assignedEngineer);
		ticketRepo.createTicket(ticket);
		tickets.add(ticket);
	}
	
	public ObservableList<Ticket> getTickets() {
	    return FXCollections.unmodifiableObservableList(tickets);	
	}
		
	
	public void updateTicket(Ticket ticket) {
		
		ticketRepo.updateTicket(ticket);
	}
	
	
	
	public void addObserver(ListChangeListener<Ticket> listener){
        tickets.addListener(listener);
	}
	
	
	
	public Ticket getTicketByNr(int nr) {
			
		  return ticketRepo.getTicketByNr(nr);
  
	  }

}
