package net.dragberry.expman.bean;

import java.util.ArrayList;
import java.util.List;

public class InstructionTO implements TransferObject {

	private static final long serialVersionUID = 382048321481968453L;
	
	private Long instructionKey;
	
	private String classification;
	
	private Long customerKey;
	
	private List<TransactionTO> transactionList = new ArrayList<>();

	public Long getInstructionKey() {
		return instructionKey;
	}

	public void setInstructionKey(Long instructionKey) {
		this.instructionKey = instructionKey;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public Long getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(Long customerKey) {
		this.customerKey = customerKey;
	}

	public List<TransactionTO> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<TransactionTO> transactionList) {
		this.transactionList = transactionList;
	}
	
	public void addTransaction(TransactionTO trTO) {
		transactionList.add(trTO);
	}
}
