package domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;





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
	
	
	@Override
	public String toString() {
		
		return String.format("%s-%s",this.userName,this.email);
	}	
	
}
