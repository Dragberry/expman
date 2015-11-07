package net.dragberry.expman.web.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.business.CustomerService;

@Controller
public class TestController {
	
	private static final Logger LOG = LogManager.getLogger(TestController.class.getName());
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		boolean b = request.isUserInRole("ROLE_ADMIN");
		b = !b;
		return "index";
	}
	
	@RequestMapping("/registration")
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView("registration");
		modelAndView.addObject("customerTO", new CustomerTO());
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registrationSubmit(CustomerTO customerTO, HttpServletRequest request) {
		LOG.info("Registration POST request. FirstName=" + customerTO.getFirstName());
		customerTO.setEnabled(true);
		Set<String> roles = new HashSet<>();
		roles.add("USER");
		customerTO.setRoles(roles);
		
		ResultTO<CustomerTO> createdCustomerTO = customerService.createCustomer(customerTO);
		if (createdCustomerTO.hasIssues()) {
			return new ModelAndView("registration");
		} else {
			return new ModelAndView("index");
		}
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
