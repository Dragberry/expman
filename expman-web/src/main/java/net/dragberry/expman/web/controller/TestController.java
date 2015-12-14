package net.dragberry.expman.web.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.dragberry.expman.bean.CounterPartyTO;
import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.bean.InterchangeTO;
import net.dragberry.expman.bean.InterchangeTypeTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.business.CounterPartyService;
import net.dragberry.expman.business.CustomerService;
import net.dragberry.expman.business.InterchangeService;
import net.dragberry.expman.business.InterchangeTypeService;
import net.dragberry.expman.web.model.CounterPartyCreateModel;
import net.dragberry.expman.web.model.InterchangeCreateModel;
import net.dragberry.expman.web.model.InterchangeTypeCreateModel;

@Controller
public class TestController {
	
	private static final Logger LOG = LogManager.getLogger(TestController.class.getName());
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private InterchangeService interchangeService;
	@Autowired
	private InterchangeTypeService interchangeTypeService;
	@Autowired
	private CounterPartyService counterPartyService;

	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		return "index";
	}
	
	@RequestMapping("/create-counter-party")
	public ModelAndView createCounterParty(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("create-counter-party");
		modelAndView.addObject("counterPartyModel", new CounterPartyCreateModel());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/create-counter-party", method = RequestMethod.POST)
	public ModelAndView createCounterParty(CounterPartyCreateModel counterPartyCreateModel, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("create-counter-party");
		modelAndView.addObject("counterPartyModel", new CounterPartyCreateModel());
		
		CounterPartyTO cpTO = new CounterPartyTO();
		cpTO.setName(counterPartyCreateModel.getName());
		
		String principalName = request.getUserPrincipal().getName();
		CustomerTO loggedCustomer = customerService.findByCustomerName(principalName).getObject();
		cpTO.setCustomer(loggedCustomer);
		
		counterPartyService.createCounterParty(cpTO);
		return new ModelAndView("index");
	}
	
	@RequestMapping("/create-interchange-type")
	public ModelAndView createInterchangeType(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("create-interchange-type");
		modelAndView.addObject("interchangeTypeModel", new InterchangeTypeCreateModel());
		modelAndView.addObject("typeList", InterchangeTypeCreateModel.Type.values());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/create-interchange-type", method = RequestMethod.POST)
	public ModelAndView createInterchangeType(InterchangeTypeCreateModel interchangeTypeCreateModel, HttpServletRequest request) {
		InterchangeTypeTO interchangeTypeTO = new InterchangeTypeTO();
		interchangeTypeTO.setName(interchangeTypeCreateModel.getName());
		interchangeTypeTO.setType(interchangeTypeCreateModel.getType());
		
		String principalName = request.getUserPrincipal().getName();
		CustomerTO loggedCustomer = customerService.findByCustomerName(principalName).getObject();
		interchangeTypeTO.setCustomer(loggedCustomer);
		
		interchangeTypeService.createInterchangeType(interchangeTypeTO);
		
		ModelAndView modelAndView = new ModelAndView("create-interchange-type");
		modelAndView.addObject("interchangeTypeModel", new InterchangeTypeCreateModel());
		modelAndView.addObject("typeList", InterchangeTypeCreateModel.Type.values());
		return modelAndView;
	}
	
	@RequestMapping("/create-interchange")
	public ModelAndView createInterchange(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("create-interchange");
		modelAndView.addObject("interchange", new InterchangeCreateModel());
		String principalName = request.getUserPrincipal().getName();
		CustomerTO loggedCustomer = customerService.findByCustomerName(principalName).getObject();
		
		List<InterchangeTypeTO> interchangeTypeList = interchangeTypeService.findInterchangeTypeListForCustomer(loggedCustomer.getCustomerKey()).getList();
		modelAndView.addObject("interchangeTypeList", interchangeTypeList);
		
		List<CounterPartyTO> counterPartyList = counterPartyService.fetchCounterPartyList(loggedCustomer.getCustomerKey()).getList();
		modelAndView.addObject("counterPartyList", counterPartyList);
		return modelAndView;
	}
	
	@RequestMapping(value = "/create-interchange", method = RequestMethod.POST)
	public ModelAndView createInterchange(InterchangeCreateModel interchange, HttpServletRequest request) {
		InterchangeTO interchangeTO = new InterchangeTO();
		interchangeTO.setAmount(interchange.getAmount());
		interchangeTO.setCurrency(interchange.getCurrency());
		interchangeTO.setDescription(interchange.getDescription());
		interchangeTO.setProcessingDate(interchange.getProcessingDate());
		
		String principalName = request.getUserPrincipal().getName();
		CustomerTO loggedCustomer = customerService.findByCustomerName(principalName).getObject();
		interchangeTO.setCustomer(loggedCustomer);
		
		InterchangeTypeTO interchangeTypeTO = interchangeTypeService.findInterchangeTypeByKey(interchange.getInterchangeTypeKey()).getObject();
		interchangeTO.setInterchangeType(interchangeTypeTO);
		
		CounterPartyTO cpTO = counterPartyService.findCounterParty(interchange.getCounterPartyKey()).getObject();
		interchangeTO.setCounterParty(cpTO);
		
		interchangeService.createInterchange(interchangeTO);
		ModelAndView modelAndView = new ModelAndView("create-interchange");
		modelAndView.addObject("interchange", new InterchangeCreateModel());
		return modelAndView;
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
	
}
