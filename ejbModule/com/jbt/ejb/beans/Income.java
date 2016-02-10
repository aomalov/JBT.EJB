package com.jbt.ejb.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="INCOME_RECS")
@NamedQueries({
	@NamedQuery(name="Income_GetAll", query="SELECT d FROM Income AS d ORDER BY d.timestamp DESC"),
	@NamedQuery(name="Income_GetAllByCustomer", query="SELECT d FROM Income AS d WHERE d.description=:description and d.invokerId=:custId ORDER BY d.timestamp DESC"),
	@NamedQuery(name="Income_GetAllByCompany", query="SELECT d FROM Income AS d WHERE d.description<>:description and d.invokerId=:compId ORDER BY d.timestamp DESC")
})
@XmlRootElement
public class Income implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5380161623075673728L;
	
	private Long id;
	private Long invokerId;
	private String invokerName;
	private Double amount;
	private Date timestamp;
	private OperationType description;
	
	
	public Income(){
		
	}
	
	public Income(Long invokerId, Double amount, Date timestamp, OperationType description, String invokerName) {
		super();
		this.invokerId = invokerId;
		this.amount = amount;
		this.timestamp = timestamp;
		this.description = description;
		this.invokerName = invokerName;
	}

	@Id
	@GeneratedValue
	@Column(name="INCOME_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInvokerId() {
		return invokerId;
	}

	public void setInvokerId(Long invokerId) {
		this.invokerId = invokerId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Enumerated(EnumType.STRING)
	public OperationType getDescription() {
		return description;
	}

	public void setDescription(OperationType description) {
		this.description = description;
	}
	

	public String getInvokerName() {
		return invokerName;
	}

	public void setInvokerName(String invokerName) {
		this.invokerName = invokerName;
	}

	@Override
	public String toString() {
		return "Income [id=" + id + ", invokerId=" + invokerId + ", amount=" + amount + ", timestamp=" + timestamp
				+ ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((invokerId == null) ? 0 : invokerId.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Income))
			return false;
		Income other = (Income) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (description != other.description)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (invokerId == null) {
			if (other.invokerId != null)
				return false;
		} else if (!invokerId.equals(other.invokerId))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}
	
	
	

}
