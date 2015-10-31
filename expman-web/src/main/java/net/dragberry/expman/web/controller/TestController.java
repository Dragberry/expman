package net.dragberry.expman.web.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.business.CustomerService;

@Controller
public class TestController {
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		boolean b = request.isUserInRole("ROLE_ADMIN");
		b = !b;
		return "index";
	}
	
	@RequestMapping("/admin")
	public String admin() {
		
		return "admin";
	}
	
	@RequestMapping("/customer")
	public String customer() {
		return "customer";
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
