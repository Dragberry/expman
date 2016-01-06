package net.dragberry.expman.bean;

public class AccountTO implements TransferObject {

	private static final long serialVersionUID = 9037417097329176003L;
	
	private Long accountKey;
	
	private String number;

	public Long getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(Long accountKey) {
		this.accountKey = accountKey;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	
}
