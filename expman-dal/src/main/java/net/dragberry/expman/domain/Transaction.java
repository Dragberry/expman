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
@Table(name = "TRANSACTION")
public class Transaction implements Serializable {

	private static final long serialVersionUID = -5641038821205965857L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANSACTION_KEY")
	private Long transactionKey;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INSTRUCTION_KEY", referencedColumnName = "INSTRUCTION_KEY")
	private Instruction instruction;
	
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	
	@Column(name = "PROCESSING_DATE")
	private Date processingDate;
	
	@Column(name = "CURRENCY")
	private String currency;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "TYPE")
	private String type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRANSACTION_TYPE_KEY", referencedColumnName = "TRANSACTION_TYPE_KEY")
	private TransactionType transactionType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTER_PARTY_KEY", referencedColumnName = "COUNTER_PARTY_KEY")
	private CounterParty counterParty;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_KEY", referencedColumnName = "ACCOUNT_KEY")
	private Account account;

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

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public CounterParty getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(CounterParty counterParty) {
		this.counterParty = counterParty;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Instruction getInstruction() {
		return instruction;
	}

	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}
	
}
