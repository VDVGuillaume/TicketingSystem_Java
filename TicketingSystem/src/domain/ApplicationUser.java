package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="AspNetUsers")
public class ApplicationUser implements Serializable {
	
	@Id	
	int Id;
	
	
}
