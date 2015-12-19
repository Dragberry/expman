package net.dragberry.expman.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import net.dragberry.expman.web.common.Constants;
import net.dragberry.expman.web.model.InterchangeCreateModel;
import net.dragberry.expman.web.security.CustomerDetails;
import net.dragberry.expman.web.security.ExpmanSecurityContext;

@Controller
public class InterchangeController implements Serializable {

	private static final long serialVersionUID = 4920765963061089750L;

	private static final Logger LOG = LogManager.getLogger(InterchangeController.class.getName());
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private InterchangeService interchangeService;
	@Autowired
	private InterchangeTypeService interchangeTypeService;
	@Autowired
	private CounterPartyService counterPartyService;
	
	@RequestMapping(Constants.Path.INTERCHANGE_CREATE)
	public ModelAndView createInterchange() {
		ModelAndView modelAndView = new ModelAndView(Constants.View.INTERCHANGE_CREATE);
		modelAndView.addObject("interchange", new InterchangeCreateModel());
		
		Long loggedCutomerKey = ExpmanSecurityContext.getCustomerKey();
		
		List<InterchangeTypeTO> interchangeTypeList = interchangeTypeService.findInterchangeTypeListForCustomer(loggedCutomerKey).getList();
		modelAndView.addObject("interchangeTypeList", interchangeTypeList);
		
		List<CounterPartyTO> counterPartyList = counterPartyService.fetchCounterPartyList(loggedCutomerKey).getList();
		modelAndView.addObject("counterPartyList", counterPartyList);
		return modelAndView;
	}
	
	@RequestMapping(value = Constants.Path.INTERCHANGE_CREATE, method = RequestMethod.POST)
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
		
		ResultTO<InterchangeTO> result = interchangeService.createInterchange(interchangeTO);
		if (result.hasIssues()) {
			ModelAndView modelAndView = new ModelAndView(Constants.View.INTERCHANGE_CREATE);
			modelAndView.addObject("interchange", new InterchangeCreateModel());
			return modelAndView;
		} else {
			return new ModelAndView(Constants.View.HOME);
		}
		
	}
}
