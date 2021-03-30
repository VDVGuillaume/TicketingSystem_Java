package controller;

import domain.Ticket;
import repository.TicketDao;
import repository.TicketDaoJpa;

public class TicketController {
	
	private TicketDao ticketRepo;
		
	
	public TicketController() {		
		ticketRepo = new TicketDaoJpa();
	}


	public Ticket getTicketByNr(int nr) {
			
		  return ticketRepo.getTicketByNr(nr);
  
	  }

}
