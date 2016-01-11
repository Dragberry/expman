package net.dragberry.expman.query;

import net.dragberry.expman.bean.TransferObject;

public class CounterPartyCreateQuery implements TransferObject {

	private static final long serialVersionUID = -212050288646221199L;
	
	private String name;
	
	private Boolean physical;
	
	private Long customerKey;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getPhysical() {
		return physical;
	}

	public void setPhysical(Boolean physical) {
		this.physical = physical;
	}

	public Long getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(Long customerKey) {
		this.customerKey = customerKey;
	}
	
}
