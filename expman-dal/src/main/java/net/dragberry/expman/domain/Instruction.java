package net.dragberry.expman.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "INSTRUCTION")
public class Instruction implements Serializable {

	private static final long serialVersionUID = -2614645869547074197L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INSTRUCTION_KEY")
	private Long instructionKey;
	
	@Enumerated
	@Column(name = "CLASSIFICATION")
	private InstructionClassification classification;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTER_PARTY_KEY", referencedColumnName = "COUNTER_PARTY_KEY")
	private CounterParty counterParty;
	
	@Column(name = "STATUS")
	private InstructionStatus status;
	
	@OneToMany(mappedBy = "instruction", fetch = FetchType.LAZY)
	private List<Transaction> transactions;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_KEY", referencedColumnName = "CUSTOMER_KEY")
	private Customer customer;

	public Long getInstructionKey() {
		return instructionKey;
	}

	public void setInstructionKey(Long instructionKey) {
		this.instructionKey = instructionKey;
	}

	public InstructionClassification getClassification() {
		return classification;
	}

	public void setClassification(InstructionClassification classification) {
		this.classification = classification;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CounterParty getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(CounterParty counterParty) {
		this.counterParty = counterParty;
	}

	public InstructionStatus getStatus() {
		return status;
	}

	public void setStatus(InstructionStatus status) {
		this.status = status;
	}
	
}
