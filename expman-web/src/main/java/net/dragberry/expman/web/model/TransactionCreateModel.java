package net.dragberry.expman.web.model;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionCreateModel implements Model {

	private static final long serialVersionUID = -2580105980906008241L;
	
	private Long transactionKey;
	
	private BigDecimal amount;
	
	private Date processingDate = new Date();
	
	private String currency;
	
	private String description;
	
	private Long transactionTypeKey;
	
	private Long counterPartyKey;
	
	private Long customerKey;

	public Long getTransactionKey() {
		return transactionKey;
	}

	public void setTransactionKey(Long transactionKey) {
		this.transactionKey = transactionKey;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getTransactionTypeKey() {
		return transactionTypeKey;
	}

	public void setTransactionTypeKey(Long transactionTypeKey) {
		this.transactionTypeKey = transactionTypeKey;
	}

	public Long getCounterPartyKey() {
		return counterPartyKey;
	}

	public void setCounterPartyKey(Long counterPartyKey) {
		this.counterPartyKey = counterPartyKey;
	}

	public Long getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(Long customerKey) {
		this.customerKey = customerKey;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
