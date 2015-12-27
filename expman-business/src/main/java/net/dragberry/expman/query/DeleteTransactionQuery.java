package net.dragberry.expman.query;

import net.dragberry.expman.bean.TransferObject;

public class DeleteTransactionQuery implements TransferObject {

	private static final long serialVersionUID = 5086412434658101823L;
	
	private Long transactionKey;
	
	private Long customerKey;

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
	
}
