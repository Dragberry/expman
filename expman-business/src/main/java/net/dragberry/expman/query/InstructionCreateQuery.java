package net.dragberry.expman.query;

import java.util.ArrayList;
import java.util.List;

import net.dragberry.expman.bean.TransferObject;

public class InstructionCreateQuery implements TransferObject {

	private static final long serialVersionUID = 324164907674874816L;
	
	private Long customerKey;
	
	private String classification;

	private List<TransactionCreateQuery> transactionList = new ArrayList<>();
	
	public Long getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(Long customerKey) {
		this.customerKey = customerKey;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public List<TransactionCreateQuery> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<TransactionCreateQuery> transactionList) {
		this.transactionList = transactionList;
	}
	
}
