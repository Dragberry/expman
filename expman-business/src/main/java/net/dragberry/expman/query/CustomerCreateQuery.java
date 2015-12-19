package net.dragberry.expman.query;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import net.dragberry.expman.bean.TransferObject;

public class CustomerCreateQuery implements TransferObject {
	
	private static final long serialVersionUID = 3019584916612756293L;

	private Long customerKey;
	
	private String customerName;
	
	private String password;
	
	private String passwordRepeated;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Date birthdate;
	
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birtdate) {
		this.birthdate = birtdate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
}
