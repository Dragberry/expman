package net.dragberry.expman.web.model;

public class TransactionTypeCreateModel implements Model {
	
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

	private Long transactionTypeKey;
	
	private String name;
	
	private String type;
	
	private Long customerKey;

	public Long getTransactionTypeKey() {
		return transactionTypeKey;
	}

	public void setTransactionTypeKey(Long transactionTypeKey) {
		this.transactionTypeKey = transactionTypeKey;
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
