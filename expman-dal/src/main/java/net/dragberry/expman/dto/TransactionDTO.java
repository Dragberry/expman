package net.dragberry.expman.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransactionDTO implements Serializable {

	private static final long serialVersionUID = -859819118037899264L;
	
	private Long transactionKey;
	
	private Long customerKey;
	
	private Long accountKey;
	
	private String accountNumber;
	
	private Long counterPartyKey;
	
	private String counterPartyName;
	
	private Long transactionTypeKey;
	
	private String transactionType;
	
	private String transactionTypeName;
	
	private String description;
	
	private Date processingDate;
	
	private BigDecimal amount;
	
	private String currency;
	
	public TransactionDTO(Long transactionKey, Long customerKey, Long accountKey, String accountNumber,
			Long counterPartyKey, String counterPartyName, Long transactionTypeKey, String transactionType,
			String transactionTypeName, String description, Date processingDate, BigDecimal amount, String currency) {
		this.transactionKey = transactionKey;
		this.customerKey = customerKey;
		this.accountKey = accountKey;
		this.accountNumber = accountNumber;
		this.counterPartyKey = counterPartyKey;
		this.counterPartyName = counterPartyName;
		this.transactionTypeKey = transactionTypeKey;
		this.transactionType = transactionType;
		this.transactionTypeName = transactionTypeName;
		this.description = description;
		this.processingDate = processingDate;
		this.amount = amount;
		this.currency = currency;
	}

	public Long getTransactionKey() {
		return transactionKey;
	}

	public void setTransactionKey(Long transactionKey) {
		this.transactionKey = transactionKey;
	}

	public Long getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(Long customerKey) {
		this.customerKey = customerKey;
	}

	public Long getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(Long accountKey) {
		this.accountKey = accountKey;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getCounterPartyKey() {
		return counterPartyKey;
	}

	public void setCounterPartyKey(Long counterPartyKey) {
		this.counterPartyKey = counterPartyKey;
	}

	public String getCounterPartyName() {
		return counterPartyName;
	}

	public void setCounterPartyName(String counterPartyName) {
		this.counterPartyName = counterPartyName;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionTypeName() {
		return transactionTypeName;
	}

	public void setTransactionTypeName(String transactionTypeName) {
		this.transactionTypeName = transactionTypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getProcessingDate() {
		return processingDate;
	}

	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getTransactionTypeKey() {
		return transactionTypeKey;
	}

	public void setTransactionTypeKey(Long transactionTypeKey) {
		this.transactionTypeKey = transactionTypeKey;
	}
	
}
