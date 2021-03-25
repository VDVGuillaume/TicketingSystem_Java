package repository;

import domain.Ticket;

import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;



public class TicketDaoJpa extends GenericDaoJpa<Ticket> implements TicketDao  {
    public TicketDaoJpa() {
        super(Ticket.class);
    }

    @Override
    public Ticket getTicketByNr(int ticketnr) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Ticket.findByNr", Ticket.class)
                 .setParameter("Ticketnr", ticketnr)
                .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
}
