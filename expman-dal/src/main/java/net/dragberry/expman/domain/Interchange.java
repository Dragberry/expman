package net.dragberry.expman.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INTERCHANGE")
public class Interchange implements Serializable {

	private static final long serialVersionUID = -5641038821205965857L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INTERCHANGE_KEY")
	private Long interchangeKey;
	
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_KEY", referencedColumnName = "CUSTOMER_KEY")
	private Customer customer;
	
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	
	@Column(name = "PROCESSING_DATE")
	private Date processingDate;
	
	@Column(name = "CURRENCY")
	private String currency;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INTERCHANGE_TYPE_KEY", referencedColumnName = "INTERCHANGE_TYPE_KEY")
	private InterchangeType interchangeType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTER_PARTY_KEY", referencedColumnName = "COUNTER_PARTY_KEY")
	private CounterParty counterParty;

	public Long getInterchangeKey() {
		return interchangeKey;
	}

	public void setInterchangeKey(Long interchangeKey) {
		this.interchangeKey = interchangeKey;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getProcessingDate() {
		return processingDate;
	}

	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public InterchangeType getInterchangeType() {
		return interchangeType;
	}

	public void setInterchangeType(InterchangeType interchangeType) {
		this.interchangeType = interchangeType;
	}

	public CounterParty getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(CounterParty counterParty) {
		this.counterParty = counterParty;
	}

}
