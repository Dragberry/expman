package net.dragberry.expman.bean;

import java.math.BigDecimal;
import java.util.Date;

public class InterchangeTO implements TransferObject {

	private static final long serialVersionUID = -7659610163078899945L;

	private Long interchangeKey;
	
	private BigDecimal amount;
	
	private Date processingDate = new Date();
	
	private String currency;
	
	private String description;
	
	private InterchangeTypeTO interchangeType;
	
	private CounterPartyTO counterParty;
	
	private CustomerTO customer;

	public Long getInterchangeKey() {
		return interchangeKey;
	}

	public void setInterchangeKey(Long interchangeKey) {
		this.interchangeKey = interchangeKey;
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

	public InterchangeTypeTO getInterchangeType() {
		return interchangeType;
	}

	public void setInterchangeType(InterchangeTypeTO interchangeType) {
		this.interchangeType = interchangeType;
	}

	public CounterPartyTO getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(CounterPartyTO counterParty) {
		this.counterParty = counterParty;
	}

	public CustomerTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerTO customer) {
		this.customer = customer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
