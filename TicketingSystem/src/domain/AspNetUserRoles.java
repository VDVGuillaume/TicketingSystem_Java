package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="AspNetUserRoles")
public class AspNetUserRoles {
	@Id
	@ManyToOne
	@JoinColumn(name="UserId")
	public ApplicationUser user;
	
	@Id
	@ManyToOne
	@JoinColumn(name="RoleId")
	public ApplicationUserRole role;

}
