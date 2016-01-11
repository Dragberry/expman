package net.dragberry.expman.query;

import net.dragberry.expman.bean.TransferObject;

public class TransactionTypeCreateQuery implements TransferObject {

	private static final long serialVersionUID = -447227191813039756L;
	
	private String name;
	
	private Long customerKey;
	
	private Long parentKey;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(Long customerKey) {
		this.customerKey = customerKey;
	}

	public Long getParentKey() {
		return parentKey;
	}

	public void setParentKey(Long parentKey) {
		this.parentKey = parentKey;
	}
	
}
