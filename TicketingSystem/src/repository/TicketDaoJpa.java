package repository;

import domain.Ticket;
import domain.Ticket;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;



public class TicketDaoJpa extends GenericDaoJpa<Ticket> implements TicketDao  {
    public TicketDaoJpa() {
        super(Ticket.class);
    }
    
    

    @Override
    public void createTicket(Ticket ticket) throws EntityExistsException {
        try {
        	 em.getTransaction().begin();
             em.persist(ticket);   
             em.getTransaction().commit();
            } catch (EntityExistsException ex) {
            throw new EntityExistsException();
        } 
    }
    
    @Override
    public void updateTicket(Ticket ticket) throws EntityNotFoundException {
        try {   
        		em.getTransaction().begin();
        		em.merge(ticket); 
        		em.getTransaction().commit();
            } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException();
        }
    }   
    
    @Override
    public List<Ticket> getTickets() throws EntityNotFoundException{
    	try {
    		return em.createNamedQuery("Ticket.getAll",Ticket.class)
    		.getResultList();    		
    	} catch (EntityNotFoundException ex) {
    		throw new EntityNotFoundException();
    	}
    }
    
    
    

    @Override
    public Ticket getTicketByNr(int ticketnr) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Ticket.findByNr", Ticket.class)
                 .setParameter("ticketnr", ticketnr)
                .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
}
