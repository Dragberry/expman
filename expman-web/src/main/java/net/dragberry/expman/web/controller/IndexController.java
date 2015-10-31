package net.dragberry.expman.web.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.business.CustomerService;

@Controller
public class IndexController {
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/create")
	public String create() {
		CustomerTO customerTO = new CustomerTO();
		customerTO.setCustomerName("Makseemka4");
		customerTO.setPassword("password");
		customerTO.setEnabled(true);
		Set<String> roles = new HashSet<>();
		roles.add("ADMIN");
		roles.add("USER");
		customerTO.setRoles(roles);
		customerService.createCustomer(customerTO);
		return "index";
	}
}
