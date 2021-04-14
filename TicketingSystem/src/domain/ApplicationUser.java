package domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;





@Entity
@NamedQueries({
	
	@NamedQuery(
		    name="ApplicationUser.findUserByUserName",
		    query="SELECT u FROM ApplicationUser u WHERE u.userName = :userName"
		)
})
@Table(name ="AspNetUsers")
public class ApplicationUser implements Serializable {
	
	@Id	
	String id;
	int clientId;
	String userName;
	String email;
	String passwordHash;
	String securityStamp;	
	String concurrencyStamp;
	Date LockoutEnd;
	Boolean LockoutEnabled;
	int AccessFailedCount;
	
	
	@ManyToMany
	@JoinTable(
	  name = "AspNetUserRoles", 
	  joinColumns = @JoinColumn(name = "UserId"), 
	  inverseJoinColumns = @JoinColumn(name = "RoleId"))
	List<ApplicationUserRole> userRoles = new ArrayList<ApplicationUserRole>();
	
	
	@Override
	public String toString() {
		
		return String.format("%s-%s",this.userName,this.email);
	}	
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getPasswordHash() {
		return this.passwordHash;
	}
	
	public void setUsername(String username) {
		this.userName = username;
	}
	
	public String getUsername() {
		return this.userName;
	}
	
	public void setLockoutEnd(Date lockoutEnd) {
		this.LockoutEnd = lockoutEnd;
	}
	
	public Date getLockoutEnd() {
		return this.LockoutEnd;
	}
	
	public void setAccessFailedCount(int accessFailedCount) {
		this.AccessFailedCount = accessFailedCount;
	}
	public int getAccessFailedCount() {
		return this.AccessFailedCount;
	}
	
	public List<ApplicationUserRole> getUserRoles(){
		return this.userRoles;
	}
	
	public void addUserRole(ApplicationUserRole role) {
		if(this.userRoles == null)
			this.userRoles = new ArrayList<ApplicationUserRole>();
		this.userRoles.add(role);
	}
}