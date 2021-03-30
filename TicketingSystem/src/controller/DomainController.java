package domain;

import repository.GenericDaoJpa;
import repository.TicketDao;
import repository.TicketDaoJpa;

public class DomainController {
	 
	private TicketDao ticketRepo;
	
	
	
	  public DomainController() {
		  ticketRepo = new TicketDaoJpa();	   
	  }
	  
	  public Ticket getTicketByNr(int nr) {
		
		  return ticketRepo.getTicketByNr(nr);
  
	  }
	  
	  
	  
	  public void close() {
	        GenericDaoJpa.closePersistency();
	    }


}
