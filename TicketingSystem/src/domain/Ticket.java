package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;
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
		),
	@NamedQuery(
		    name="Ticket.getAll",
		    query="SELECT t FROM Ticket t"
		)
	
})
@Table(name="Tickets")
public class Ticket implements Serializable {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
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
	
	
	public Ticket(String title, String description, TicketType type, Client client, 
			Contract contract, ApplicationUser assignedEngineer) {
		setTitle(title);
		setDescription(description);
		setType(type);
		setClient(client);
		setContract(contract);
		setAssignedEngineer(assignedEngineer);
		setComments(new ArrayList<Comment>());
		setAttachments(new ArrayList<Attachment>());	
		
		this.dateAdded = new Date();
		this.status = TicketStatus.Aangemaakt;
	}
	
	protected Ticket() {
		
	}
	
	@Override	
	public String toString() {
		return String.format("TicketId %d - Title : %s",this.ticketnr,this.title);
	}


	public int getTicketnr() {
		return ticketnr;
	}


	public void setTicketnr(int ticketnr) {
		this.ticketnr = ticketnr;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public TicketStatus getStatus() {
		return status;
	}


	public void setStatus(TicketStatus status) {
		this.status = status;
	}


	public Date getDateAdded() {
		return dateAdded;
	}


	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}


	public Date getDateClosed() {
		return dateClosed;
	}


	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public ApplicationUser getAssignedEngineer() {
		return assignedEngineer;
	}


	public void setAssignedEngineer(ApplicationUser assignedEngineer) {
		this.assignedEngineer = assignedEngineer;
	}


	public TicketType getType() {
		return type;
	}


	public void setType(TicketType type) {
		this.type = type;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public List<Attachment> getAttachments() {
		return attachments;
	}


	public void setAttachments(ArrayList<Attachment> arrayList) {
		this.attachments = arrayList;
	}


	public Contract getContract() {
		return contract;
	}


	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	
	
	
}
