package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="ContractTypes")
public class ContractType implements Serializable {
	
	@Id
	@Column(name="ContractTypeId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	boolean active;
	TicketCreationTime ticketCreationTime;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name="ContractTypeTicketCreationType",
		joinColumns = @JoinColumn(name = "ContractTypeId"),
		inverseJoinColumns = @JoinColumn(name = "TicketCreationTypeId")
	)
	List<TicketCreationType> ticketCreationTypes;
	
	public ContractType(String name, boolean active, TicketCreationTime ticketCreationTime) 
	{
		setName(name);
		setActive(active);
		setTicketcreationTime(ticketCreationTime);
		this.ticketCreationTypes = new ArrayList<TicketCreationType>();
	}
	
	protected ContractType() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public TicketCreationTime getTicketcreationTime() {
		return ticketCreationTime;
	}

	public void setTicketcreationTime(TicketCreationTime ticketCreationTime) {
		this.ticketCreationTime = ticketCreationTime;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s",this.getName());
	}
}
