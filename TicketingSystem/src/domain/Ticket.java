package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
	
	@NamedQuery(
		    name="Ticket.findByNr",
		    query="SELECT t FROM Ticket t WHERE t.ticketnr = :ticketnr"
		)	
	
})
@Table(name="Tickets")
public class Ticket implements Serializable {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Ticketnr")
	public int ticketnr;
	@Column(name = "Title")
	public String title;
	public TicketStatus status;
	public Date dateAdded;
	public Date dateClosed;
	public String description;
	@ManyToOne
	@JoinColumn(name="ClientID")
	public Client client;
	@OneToOne
	@JoinColumn(name="AssignedEngineerId")
	public ApplicationUser assignedEngineer;
	@OneToOne
	@JoinColumn(name = "TypeId")
	public TicketType type;
	@OneToMany
	public List<Comment> comments;
	@OneToMany
	public List<Attachment> attachments;
	@ManyToOne
	@JoinColumn(name = "ContractId")
	public Contract contract;
	
	
	
	@Override	
	public String toString() {
		return String.format("TicketId %d - Title : %s",this.ticketnr,this.title);
	}
	
	
}
