package domain;

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
@Table(name="TicketCreationTypes")
public class TicketCreationType {
	
	@Id
	@Column(name="TicketCreationTypeId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int ticketCreationTypeId;
	TicketCreationTypeName name;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name="ContractTypeTicketCreationType",
		joinColumns = @JoinColumn(name = "TicketCreationTypeId"),
		inverseJoinColumns = @JoinColumn(name = "ContractTypeId")
	)
	List<ContractType> ContractTypes;
	
	public TicketCreationType(TicketCreationTypeName name) {
		setName(name);
		this.ContractTypes = new ArrayList<ContractType>();
	}
	
	protected TicketCreationType() {}

	public TicketCreationTypeName getName() {
		return name;
	}

	public void setName(TicketCreationTypeName name) {
		this.name = name;
	}

	
	
	
}
