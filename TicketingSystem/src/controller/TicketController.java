package controller;

import domain.Client;
import domain.Ticket;
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

	public void createTicket() {
		
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
