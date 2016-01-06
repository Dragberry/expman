package net.dragberry.expman.query;

import net.dragberry.expman.bean.TransferObject;

public class AccountBalanceQuery implements TransferObject {

	private static final long serialVersionUID = -8495724020159862796L;
	
	private Long customerKey;

	public Long getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(Long customerKey) {
		this.customerKey = customerKey;
	}
	
}
