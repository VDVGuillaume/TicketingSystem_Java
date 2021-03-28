package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="Contracts")
public class Contract implements Serializable {

	
	@Id
	@Column(name="ContractId")
	int commentId;
}
