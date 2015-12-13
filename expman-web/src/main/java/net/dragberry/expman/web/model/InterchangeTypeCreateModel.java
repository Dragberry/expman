package net.dragberry.expman.web.model;

public class InterchangeTypeCreateModel implements Model {
	
	public static enum Type {
		
		C("Credit"), D("Debit");
		
		private String title;
		
		private Type(String title) {
			this.title = title;
		}
		
		public String getTitle() {
			return title;
		}
	}

	private static final long serialVersionUID = 1210788975972018729L;

	private Long interchangeTypeKey;
	
	private String name;
	
	private String type;
	
	private Long customerKey;

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

	public Long getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(Long customerKey) {
		this.customerKey = customerKey;
	}
}
