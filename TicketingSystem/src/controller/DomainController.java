package controller;

import controller_interfaces.IDomainController;
import domain.Ticket;
import repository.GenericDaoJpa;
import repository.TicketDao;
import repository.TicketDaoJpa;

public class DomainController implements IDomainController {	
	  public DomainController() {
	
	  }

	  public void close() {
	        GenericDaoJpa.closePersistency();
	    }


}
