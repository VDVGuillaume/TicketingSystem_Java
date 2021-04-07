package domain;

import java.util.ArrayList;
import java.util.List;

public class ContractType {
	
	int contractTypeId;
	String name;
	boolean active;
	List<TicketCreationType> ticketCreationTypes;
	TicketCreationTime ticketcreationTime;
	
	public ContractType(String name, boolean active, TicketCreationTime ticketCreationTime) 
	{
		setName(name);
		setActive(active);
		setTicketcreationTime(ticketCreationTime);
		this.ticketCreationTypes = new ArrayList<TicketCreationType>();
	}

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
		return ticketcreationTime;
	}

	public void setTicketcreationTime(TicketCreationTime ticketcreationTime) {
		this.ticketcreationTime = ticketcreationTime;
	}
	
	
	

}
