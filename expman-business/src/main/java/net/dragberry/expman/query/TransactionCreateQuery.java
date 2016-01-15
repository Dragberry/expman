package net.dragberry.expman.query;

import java.math.BigDecimal;
import java.util.Date;

import net.dragberry.expman.bean.TransferObject;

public class TransactionCreateQuery implements TransferObject {

	private static final long serialVersionUID = -1515663669490575594L;
	
	public static String AMOUNT_FIELD = "amount";
	public static String CURRENCY_FIELD = "currency";
	public static String DESCRIPTION_FIELD = "description";
	public static String TYPE_FIELD = "type";
	
	private Long transactionKey;
	
	private BigDecimal amount;
	
	private Date processingDate = new Date();
	
	private String currency;
	
	private String description;
	
	private Long counterPartyKey;
	
	private Long instructionKey;
	
	private Long transactionTypeKey;
	
	private Long accountKey;
	
	private String type;

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

	public Long getCounterPartyKey() {
		return counterPartyKey;
	}

	public void setCounterPartyKey(Long counterPartyKey) {
		this.counterPartyKey = counterPartyKey;
	}

	public Long getInstructionKey() {
		return instructionKey;
	}

	public void setInstructionKey(Long instructionKey) {
		this.instructionKey = instructionKey;
	}

	public Long getTransactionTypeKey() {
		return transactionTypeKey;
	}

	public void setTransactionTypeKey(Long transactionTypeKey) {
		this.transactionTypeKey = transactionTypeKey;
	}

	public Long getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(Long accountKey) {
		this.accountKey = accountKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
