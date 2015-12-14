package net.dragberry.expman.web.model;

public class CounterPartyCreateModel implements Model {

	private static final long serialVersionUID = -5093017067571686674L;

	private Long counterPartyKey;
	
	private String name;

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
	
	
}
