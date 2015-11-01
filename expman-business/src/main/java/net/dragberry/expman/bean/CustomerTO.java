package net.dragberry.expman.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CustomerTO implements TransferObject {

	private static final long serialVersionUID = -8863695384110015264L;
	
	private Long customerKey;
	
	private String customerName;
	
	private String password;
	
	private String passwordRepeated;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Date birtdate;
	
	private boolean enabled;
	
	private Set<String> roles = new HashSet<>();

	public Long getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(Long customerKey) {
		this.customerKey = customerKey;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPasswordRepeated() {
		return passwordRepeated;
	}

	public void setPasswordRepeated(String passwordRepeated) {
		this.passwordRepeated = passwordRepeated;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirtdate() {
		return birtdate;
	}

	public void setBirtdate(Date birtdate) {
		this.birtdate = birtdate;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
