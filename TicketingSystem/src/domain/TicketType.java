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
}
