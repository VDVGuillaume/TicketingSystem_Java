package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(
	    name="Contract.getAll",
	    query="SELECT c FROM Contract c"
	),
	@NamedQuery(
	    name="Contract.getById",
	    query="SELECT c FROM Contract c WHERE c.id = :id"
	)
})
@Table(name="Contracts")
public class Contract implements Serializable {
	
	@Id
	@Column(name="ContractId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	Date validFrom;
	Date validTo;
	Date dateCreated;
	Date dateClosed;
	@ManyToOne()
	@JoinColumn(name="TypeContractTypeId")
	ContractType type;
	ContractStatus status;
	
	@ManyToOne
	@JoinColumn(name="ClientID")
	public Client client;
	
	public enum ContractStatus {
		InAanvraag,
    Lopend,
    Beëindigd
	}
	
	public Contract(Date validFrom, Date validTo,	Date dateCreated,	Date dateClosed,
			ContractType type, ContractStatus status, Client client) {
		
	}
	
	protected Contract() {}
	
	public int getId() {
		return this.id;
	}
	
	public Date getValidFrom() {
		return this.validFrom;
	}
	
	public Date getValidTo() {
		return this.validTo;
	}
	
	public Date getDateCreated() {
		return this.dateCreated;
	}
	
	public Date getDateClosed() {
		return this.dateClosed;
	}
	/*
	public ContractType getType() {
		return this.type;
	}
	*/
	public ContractStatus getStatus() {
		return this.status;
	}
	
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}
	/*
	public void setType(ContractType type) {
		this.type = type;
	}
	*/
	public void setStatus(ContractStatus status) {
		this.status = status;
	}
}
