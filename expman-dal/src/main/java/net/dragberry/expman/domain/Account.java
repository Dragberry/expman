package net.dragberry.expman.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {

	private static final long serialVersionUID = -5743669081979815819L;
	
	public static enum AccountType {
		CASH, ELECTRONIC
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_KEY")
	private Long accountKey;
	@Column(name = "NUMBER")
	private String number;
	@Column(name = "CURRENCY")
	private String currency;
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_KEY", referencedColumnName = "CUSTOMER_KEY")
	private Customer customer;
	@Enumerated
	@Column(name = "TYPE")
	private AccountType type;

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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

}
