package domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

@Entity
@NamedQuery(
	    name="Ticket.findByNr",
	    query="SELECT t FROM Tickets t WHERE t.Ticketnr = :ticketnr"
	)


public class Ticket implements Serializable {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int ticketnr;
	public String title;
	public TicketStatus status;
	public DateTime dateAdded;
	public DateTime dateClosed;
	public String description;
	public Client client;
	public ApplicationUser assignedEngineer;
	public TicketType type;
	@ManyToOne
	public List<Comment> comments;
	@ManyToOne
	public List<Attachment> attachments;
	public Contract contract;
	
	
}
