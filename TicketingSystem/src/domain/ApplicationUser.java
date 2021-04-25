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
import javax.persistence.ManyToOne;
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
		),
	
	@NamedQuery(
		    name="ApplicationUser.getCustomers",
		    query="SELECT DISTINCT u FROM ApplicationUser u"
		    		+ " INNER JOIN AspNetUserRoles ur_link on ur_link.userId = u.id"
		    		+ " Inner join ApplicationUserRole r on r.id = ur_link.roleId"
		    		+ " WHERE r.name = 'CUSTOMER'"
		),
	
	@NamedQuery(
		    name="ApplicationUser.getEmployees",
		    query="SELECT DISTINCT u FROM ApplicationUser u"
		    		+ " INNER JOIN AspNetUserRoles ur_link on ur_link.userId = u.id"
		    		+ " INNER JOIN ApplicationUserRole r on r.id = ur_link.roleId"
		    		+ " WHERE r.name in ('SUPPORTMANAGER', 'TECHNICIAN')"
		)
})
@Table(name ="AspNetUsers")
public class ApplicationUser implements Serializable {
	
	@Id	
	String id;
	String userName;
	String email;
	String passwordHash;
	String securityStamp;	
	String concurrencyStamp;
	Date LockoutEnd;
	Boolean LockoutEnabled;
	int AccessFailedCount;
	String firstName;
	String lastName;
	
	@ManyToOne
	@JoinColumn(name="ClientID")
	public Client client;
		
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
	
	public void setUserName(String username) {
		this.userName = username;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
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
	
	public String getStatus() {
		if(getAccessFailedCount() >= 5) {
			return "Geblokkeerd";
		}
		
		return "Actief";
	}
	
	public String getRole() {
		return this.userRoles.get(0).getName();		
	}
	
	public String getCompany() {
		if(client == null)
			return "";
		
		return client.getName();
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
}