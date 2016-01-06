package net.dragberry.expman.bean;

import java.math.BigDecimal;

public class AccountBalanceTO implements TransferObject {

	private static final long serialVersionUID = 1245342625709910183L;
	
	private String number;
	
	private String type;
	
	private String currency;
	
	private BigDecimal balance;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
