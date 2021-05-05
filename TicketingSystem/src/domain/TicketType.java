package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="TicketTypes")
public class TicketType implements Serializable {

	@Id
	@Column(name="Id")
	int id;
	String Name;
	int RequiredSLA;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getRequiredSLA() {
		return RequiredSLA;
	}
	public void setRequiredSLA(int requiredSLA) {
		RequiredSLA = requiredSLA;
	}
	public int getId() {
		return id;
	}
	
	
}
