package repository;

import domain.Ticket;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

public interface TicketDao extends GenericDao<Ticket>  {
	  public void createTicket(Ticket ticket) throws EntityExistsException;  
      public List<Ticket> getTickets() throws EntityNotFoundException; 
      public void updateTicket(Ticket ticket) throws EntityNotFoundException;
      public Ticket getTicketByNr(int nr) throws EntityNotFoundException;   
}