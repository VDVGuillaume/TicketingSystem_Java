package domain;

import java.util.ArrayList;
import java.util.List;

public class TicketCreationType {
	
	int ticketCreationTypeId;
	TicketCreationTypeName name;
	List<ContractType> ContractTypes;
	
	public TicketCreationType(TicketCreationTypeName name) {
		setName(name);
		this.ContractTypes = new ArrayList<ContractType>();
	}

	public TicketCreationTypeName getName() {
		return name;
	}

	public void setName(TicketCreationTypeName name) {
		this.name = name;
	}

	
	
	
}
