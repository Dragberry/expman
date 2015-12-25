package net.dragberry.expman.web.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.bean.TransactionTypeTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.business.CustomerService;
import net.dragberry.expman.business.TransactionTypeService;
import net.dragberry.expman.web.common.Constants;
import net.dragberry.expman.web.model.TransactionTypeCreateModel;

@Controller
public class TransactionTypeController implements Serializable {

	private static final long serialVersionUID = -4858539408539281028L;
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private TransactionTypeService transactionTypeService;
	
	@RequestMapping(value = Constants.Path.TRANSACTION_TYPE_CREATE)
	public ModelAndView createTransactionType(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(Constants.View.TRANSACTION_TYPE_CREATE);
		modelAndView.addObject("transactionTypeModel", new TransactionTypeCreateModel());
		modelAndView.addObject("typeList", TransactionTypeCreateModel.Type.values());
		
		return modelAndView;
	}
	
	@RequestMapping(value = Constants.Path.TRANSACTION_TYPE_CREATE, method = RequestMethod.POST)
	public ModelAndView createTransactionType(TransactionTypeCreateModel transactionTypeCreateModel, HttpServletRequest request) {
		TransactionTypeTO transactionTypeTO = new TransactionTypeTO();
		transactionTypeTO.setName(transactionTypeCreateModel.getName());
		transactionTypeTO.setType(transactionTypeCreateModel.getType());
		
		String principalName = request.getUserPrincipal().getName();
		CustomerTO loggedCustomer = customerService.findByCustomerName(principalName).getObject();
		transactionTypeTO.setCustomer(loggedCustomer);
		
		ResultTO<TransactionTypeTO> result = transactionTypeService.createTransactionType(transactionTypeTO);
		if (result.hasIssues()) {
			ModelAndView modelAndView = new ModelAndView(Constants.View.TRANSACTION_TYPE_CREATE);
			modelAndView.addObject("transactionTypeModel", new TransactionTypeCreateModel());
			modelAndView.addObject("typeList", TransactionTypeCreateModel.Type.values());
			return modelAndView;
		} else {
			return new ModelAndView(Constants.View.HOME);
		}
	}

}
