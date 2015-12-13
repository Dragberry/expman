package net.dragberry.expman.bean;


public class CounterPartyTO implements TransferObject {

	private static final long serialVersionUID = -7363224827783433038L;
	
	private Long counterPartyKey;
	
	private String name;
	
	private CustomerTO customer;

	public Long getCounterPartyKey() {
		return counterPartyKey;
	}

	public void setCounterPartyKey(Long counterPartyKey) {
		this.counterPartyKey = counterPartyKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CustomerTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerTO customer) {
		this.customer = customer;
	}

}
