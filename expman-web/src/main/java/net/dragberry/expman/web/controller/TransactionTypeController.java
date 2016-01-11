package net.dragberry.expman.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.dragberry.expman.bean.TransactionTypeTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.business.TransactionTypeService;
import net.dragberry.expman.query.TransactionTypeCreateQuery;
import net.dragberry.expman.web.common.Constants;
import net.dragberry.expman.web.security.ExpmanSecurityContext;

@Controller
public class TransactionTypeController implements Serializable {

	private static final String TRANSACTION_TYPE_MODEL = "transactionType";
	
	private static final String PARENT_TYPE_LIST = "parentTypeList";

	private static final long serialVersionUID = -4858539408539281028L;
	
	@Autowired
	private TransactionTypeService transactionTypeService;
	
	@RequestMapping(value = Constants.Path.TRANSACTION_TYPE_CREATE)
	public ModelAndView createTransactionType(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(Constants.View.TRANSACTION_TYPE_CREATE);
		modelAndView.addObject(TRANSACTION_TYPE_MODEL, new TransactionTypeCreateQuery());
		List<TransactionTypeTO> parentTypeList = transactionTypeService.findTransactionTypeListForCustomer(ExpmanSecurityContext.getCustomerKey()).getList();
		modelAndView.addObject(PARENT_TYPE_LIST, parentTypeList);
		return modelAndView;
	}
	
	@RequestMapping(value = Constants.Path.TRANSACTION_TYPE_CREATE, method = RequestMethod.POST)
	public ModelAndView createTransactionType(TransactionTypeCreateQuery query, HttpServletRequest request) {
		query.setCustomerKey(ExpmanSecurityContext.getCustomerKey());
		ResultTO<TransactionTypeTO> result = transactionTypeService.createTransactionType(query);
		if (result.hasIssues()) {
			ModelAndView modelAndView = new ModelAndView(Constants.View.TRANSACTION_TYPE_CREATE);
			modelAndView.addObject(TRANSACTION_TYPE_MODEL, query);
			return modelAndView;
		} else {
			return new ModelAndView(Constants.View.HOME);
		}
	}

}
