package net.dragberry.expman.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION_TYPE")
public class TransactionType implements Serializable {
	
	private static final long serialVersionUID = 7036757795385197011L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANSACTION_TYPE_KEY")
	private Long transactionTypeKey;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "TYPE")
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_KEY", referencedColumnName = "CUSTOMER_KEY")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "PARENT_KEY", referencedColumnName = "TRANSACTION_TYPE_KEY")
	private TransactionType parentType;

	public Long getTransactionTypeKey() {
		return transactionTypeKey;
	}

	public void setTransactionTypeKey(Long transactionTypeKey) {
		this.transactionTypeKey = transactionTypeKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public TransactionType getParentType() {
		return parentType;
	}

	public void setParentType(TransactionType parentType) {
		this.parentType = parentType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
