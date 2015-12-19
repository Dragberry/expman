package net.dragberry.expman.web.controller;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.business.CustomerService;
import net.dragberry.expman.query.CustomerCreateQuery;
import net.dragberry.expman.util.RoleProvider;
import net.dragberry.expman.web.common.Constants;

@Controller
public class RegisterController implements Serializable {

	private static final long serialVersionUID = 7763768000577347838L;

	private static final String CUSTOMER_QUERY = "customerQuery";
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = Constants.Path.REGISTRATION)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView(Constants.View.REGISTRATION);
		modelAndView.addObject(CUSTOMER_QUERY, new CustomerCreateQuery());
		return modelAndView;
	}
	
	@RequestMapping(value = Constants.Path.REGISTRATION, method = RequestMethod.POST)
	public ModelAndView registration(CustomerCreateQuery customerQuery, HttpServletRequest request) {
		
		customerQuery.setEnabled(true);
		Set<String> roles = new HashSet<>();
		roles.add(RoleProvider.CUSTOMER);
		customerQuery.setRoles(roles); 
		
		ResultTO<CustomerTO> createdCustomerTO = customerService.createCustomer(customerQuery);
		if (createdCustomerTO.hasIssues()) {
			ModelAndView modelAndView = new ModelAndView(Constants.View.REGISTRATION);
			modelAndView.addObject(CUSTOMER_QUERY, new CustomerCreateQuery());
			return modelAndView;
		} else {
			return new ModelAndView(Constants.View.HOME_REDIRECT);
		}
	}

}
