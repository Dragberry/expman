package net.dragberry.expman.query;

import net.dragberry.expman.bean.TransferObject;

public class AccountQuery implements TransferObject {

	private static final long serialVersionUID = -8766399340679431144L;

	private Long customerKey;

	public Long getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(Long customerKey) {
		this.customerKey = customerKey;
	}
	
}
