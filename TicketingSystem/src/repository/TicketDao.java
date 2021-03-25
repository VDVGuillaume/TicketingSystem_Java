package repository;
import domain.Ticket;
import javax.persistence.EntityNotFoundException;

public interface TicketDao extends GenericDao<Ticket>  {
        public Ticket getTicketByNr(int nr) throws EntityNotFoundException;   
}