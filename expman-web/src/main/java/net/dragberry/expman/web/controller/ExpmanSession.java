package net.dragberry.expman.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.dragberry.expman.bean.CustomerTO;

@Controller
@Scope("session")
public class ExpmanSession {
	
	private CustomerTO loggedCustomer;
	
	public void setLoggedCustomer(CustomerTO customer) {
		this.loggedCustomer = customer;
	}
	
	public CustomerTO getLoggedCustomer() {
		return loggedCustomer;
	}

}
