package domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

@Entity
@NamedQueries({
})
@Table(name ="AspNetRoles")
public class ApplicationUserRole implements Serializable {
	
	public ApplicationUserRole() {
		
	}
	
	public ApplicationUserRole(String name) {
		this.name = name;
	}
	
	@Id	
	String id;
	String name;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}