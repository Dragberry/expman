package net.dragberry.expman.web.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.bean.InterchangeTypeTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.business.CustomerService;
import net.dragberry.expman.business.InterchangeTypeService;
import net.dragberry.expman.web.common.Constants;
import net.dragberry.expman.web.model.InterchangeTypeCreateModel;

@Controller
public class InterchangeTypeController implements Serializable {

	private static final long serialVersionUID = -4858539408539281028L;
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private InterchangeTypeService interchangeTypeService;
	
	@RequestMapping(value = Constants.Path.INTERCHANGE_TYPE_CREATE)
	public ModelAndView createInterchangeType(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(Constants.View.INTERCHANGE_TYPE_CREATE);
		modelAndView.addObject("interchangeTypeModel", new InterchangeTypeCreateModel());
		modelAndView.addObject("typeList", InterchangeTypeCreateModel.Type.values());
		
		return modelAndView;
	}
	
	@RequestMapping(value = Constants.Path.INTERCHANGE_TYPE_CREATE, method = RequestMethod.POST)
	public ModelAndView createInterchangeType(InterchangeTypeCreateModel interchangeTypeCreateModel, HttpServletRequest request) {
		InterchangeTypeTO interchangeTypeTO = new InterchangeTypeTO();
		interchangeTypeTO.setName(interchangeTypeCreateModel.getName());
		interchangeTypeTO.setType(interchangeTypeCreateModel.getType());
		
		String principalName = request.getUserPrincipal().getName();
		CustomerTO loggedCustomer = customerService.findByCustomerName(principalName).getObject();
		interchangeTypeTO.setCustomer(loggedCustomer);
		
		ResultTO<InterchangeTypeTO> result = interchangeTypeService.createInterchangeType(interchangeTypeTO);
		if (result.hasIssues()) {
			ModelAndView modelAndView = new ModelAndView(Constants.View.INTERCHANGE_TYPE_CREATE);
			modelAndView.addObject("interchangeTypeModel", new InterchangeTypeCreateModel());
			modelAndView.addObject("typeList", InterchangeTypeCreateModel.Type.values());
			return modelAndView;
		} else {
			return new ModelAndView(Constants.View.HOME);
		}
	}

}
