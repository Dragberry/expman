package net.dragberry.expman.bean;

public class InterchangeTypeTO implements TransferObject {

	private static final long serialVersionUID = 7453370608750717283L;

	private Long interchangeTypeKey;
	
	private String name;
	
	private String type;
	
	private CustomerTO customer;

	public Long getInterchangeTypeKey() {
		return interchangeTypeKey;
	}

	public void setInterchangeTypeKey(Long interchangeTypeKey) {
		this.interchangeTypeKey = interchangeTypeKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CustomerTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerTO customer) {
		this.customer = customer;
	}
	
}
