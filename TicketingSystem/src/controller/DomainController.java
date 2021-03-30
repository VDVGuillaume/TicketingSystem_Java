package controller;

import domain.Ticket;
import repository.GenericDaoJpa;
import repository.TicketDao;
import repository.TicketDaoJpa;

public class DomainController {
	 
	private TicketDao ticketRepo;
	private ContractController contractController;
	private LoginController loginController;
	private TicketController ticketController;
	
	
	  public DomainController() {
		  ticketRepo = new TicketDaoJpa();
		  contractController = new ContractController();
		  loginController = new LoginController();
		  ticketController = new TicketController();
	  }
	  
	  public Ticket getTicketByNr(int nr) {
		
		  return ticketRepo.getTicketByNr(nr);
  
	  }
	  
	  
	  
	  public void close() {
	        GenericDaoJpa.closePersistency();
	    }


}
