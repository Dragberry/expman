package net.dragberry.expman.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.business.CustomerService;
import net.dragberry.expman.query.CustomerCreateQuery;
import net.dragberry.expman.util.RoleProvider;
import net.dragberry.expman.web.common.Constants;
import net.dragberry.expman.web.controller.error.IssueResolver;

import static net.dragberry.expman.messages.BusinessMessageCodes.*;

@Controller
public class RegisterController implements Serializable {

	private static final long serialVersionUID = 7763768000577347838L;

	private static final String CUSTOMER_QUERY = "customerQuery";
	
	private static final Map<String, String> errorMap = new HashMap<>();
	
	static {
		errorMap.put(CreateCustomer.EMAIL_IS_MANDATORY, CustomerCreateQuery.EMAIL_FIELD);
		errorMap.put(CreateCustomer.EMAIL_IS_NOT_UNIQUE, CustomerCreateQuery.EMAIL_FIELD);
		errorMap.put(CreateCustomer.EMAIL_IS_INVALID, CustomerCreateQuery.EMAIL_FIELD);
		errorMap.put(CreateCustomer.PASSWORD_DOES_NOT_MATCH, CustomerCreateQuery.PASSWORD_FIELD);
		errorMap.put(CreateCustomer.PASSWORD_HAS_INVALID_LENGTH, CustomerCreateQuery.PASSWORD_FIELD);
		errorMap.put(CreateCustomer.CUSTOMER_NAME_IS_MANDATORY, CustomerCreateQuery.CUSTOMER_NAME_FIELD);
		errorMap.put(CreateCustomer.CUSTOMER_NAME_IS_NOT_UNIQUE, CustomerCreateQuery.CUSTOMER_NAME_FIELD);
	}
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = Constants.Path.REGISTRATION)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView(Constants.View.REGISTRATION);
		modelAndView.addObject(CUSTOMER_QUERY, new CustomerCreateQuery());
		return modelAndView;
	}
	
	@RequestMapping(value = Constants.Path.REGISTRATION, method = RequestMethod.POST)
	public ModelAndView registration(@ModelAttribute(CUSTOMER_QUERY) @Valid CustomerCreateQuery customerQuery, BindingResult bindingResult, HttpServletRequest request) {
		
		customerQuery.setEnabled(true);
		Set<String> roles = new HashSet<>();
		roles.add(RoleProvider.CUSTOMER);
		customerQuery.setRoles(roles); 
		
		ResultTO<CustomerTO> createdCustomerTO = customerService.createCustomer(customerQuery);
		if (createdCustomerTO.hasIssues()) {
			ModelAndView modelAndView = new ModelAndView(Constants.View.REGISTRATION);
			Map<String, Object> errorModel = IssueResolver.resolve(createdCustomerTO.getIssueLog(), errorMap, bindingResult);
			modelAndView.addAllObjects(errorModel);
			modelAndView.addObject(CUSTOMER_QUERY, customerQuery);
			return modelAndView;
		} else {
			return new ModelAndView(Constants.View.HOME_REDIRECT);
		}
	}

}
