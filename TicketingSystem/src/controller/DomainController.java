package controller;

import domain.Ticket;
import repository.GenericDaoJpa;
import repository.TicketDao;
import repository.TicketDaoJpa;

public class DomainController {	
	  public DomainController() {
	
	  }

	  public void close() {
	        GenericDaoJpa.closePersistency();
	    }


}
