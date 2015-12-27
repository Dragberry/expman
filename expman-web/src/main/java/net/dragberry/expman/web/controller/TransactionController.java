package net.dragberry.expman.web.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.dragberry.expman.bean.CounterPartyTO;
import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.bean.TransactionTO;
import net.dragberry.expman.bean.TransactionTypeTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.business.CounterPartyService;
import net.dragberry.expman.business.CustomerService;
import net.dragberry.expman.business.TransactionService;
import net.dragberry.expman.business.TransactionTypeService;
import net.dragberry.expman.query.DeleteTransactionQuery;
import net.dragberry.expman.web.common.Constants;
import net.dragberry.expman.web.model.TransactionCreateModel;
import net.dragberry.expman.web.security.ExpmanSecurityContext;

@Controller
public class TransactionController implements Serializable {

	private static final String TRANSACTION = "transaction";

	private static final String COUNTER_PARTY_LIST = "counterPartyList";

	private static final String TRANSACTION_TYPE_LIST = "transactionTypeList";

	private static final long serialVersionUID = 4920765963061089750L;

	private static final Logger LOG = LogManager.getLogger(TransactionController.class.getName());
	
	private static final String TRANSACTION_LIST = "transactionList";

	@Autowired
	private CustomerService customerService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private TransactionTypeService transactionTypeService;
	@Autowired
	private CounterPartyService counterPartyService;
	
	@RequestMapping(value = Constants.Path.TRANSACTION_DELETE)
	public ModelAndView deleteTransaction(@PathVariable("transactionId") Long transactionId) {
		DeleteTransactionQuery query = new DeleteTransactionQuery();
		query.setTransactionKey(transactionId);
		query.setCustomerKey(ExpmanSecurityContext.getCustomerKey());
		ResultTO<TransactionTO> trTO = transactionService.deleteTransaction(query);
		return new ModelAndView("redirect:/transaction/list");
	}
	
	@RequestMapping(value = Constants.Path.TRANSACTION_LIST)
	public ModelAndView listTransaction() {
		List<TransactionTO> transactionList = transactionService.fetchTransactions().getList();
		ModelAndView modelAndView = new ModelAndView(Constants.View.TRANSACTION_LIST);
		modelAndView.addObject(TRANSACTION_LIST, transactionList);
		return modelAndView;
	}
	
	@RequestMapping(value = Constants.Path.TRANSACTION_CREATE)
	public ModelAndView createTransaction() {
		ModelAndView modelAndView = new ModelAndView(Constants.View.TRANSACTION_CREATE);
		modelAndView.addObject(TRANSACTION, new TransactionCreateModel());
		
		Long loggedCutomerKey = ExpmanSecurityContext.getCustomerKey();
		
		List<TransactionTypeTO> transactionTypeList = transactionTypeService.findTransactionTypeListForCustomer(loggedCutomerKey).getList();
		modelAndView.addObject(TRANSACTION_TYPE_LIST, transactionTypeList);
		
		List<CounterPartyTO> counterPartyList = counterPartyService.fetchCounterPartyList(loggedCutomerKey).getList();
		modelAndView.addObject(COUNTER_PARTY_LIST, counterPartyList);
		return modelAndView;
	}
	
	@RequestMapping(value = Constants.Path.TRANSACTION_CREATE, method = RequestMethod.POST)
	public ModelAndView createTransaction(TransactionCreateModel transaction, HttpServletRequest request, BindingResult bindingResult) {
		bindingResult.rejectValue("currency", "messages.error");
		TransactionTO transactionTO = new TransactionTO();
		transactionTO.setAmount(transaction.getAmount());
		transactionTO.setCurrency(transaction.getCurrency());
		transactionTO.setDescription(transaction.getDescription());
		transactionTO.setProcessingDate(transaction.getProcessingDate());
		
		String principalName = request.getUserPrincipal().getName();
		CustomerTO loggedCustomer = customerService.findByCustomerName(principalName).getObject();
		transactionTO.setCustomer(loggedCustomer);
		
		TransactionTypeTO transactionTypeTO = transactionTypeService.findTransactionTypeByKey(transaction.getTransactionTypeKey()).getObject();
		transactionTO.setTransactionType(transactionTypeTO);
		
		CounterPartyTO cpTO = counterPartyService.findCounterParty(transaction.getCounterPartyKey()).getObject();
		transactionTO.setCounterParty(cpTO);
		
		ResultTO<TransactionTO> result = transactionService.createTransaction(transactionTO);
		if (!result.hasIssues()) {
			ModelAndView modelAndView = new ModelAndView(Constants.View.TRANSACTION_CREATE);
			modelAndView.addObject(TRANSACTION, new TransactionCreateModel());
			modelAndView.addAllObjects(bindingResult.getModel());
			if (bindingResult.hasErrors()) {
				loggedCustomer.equals(null);
			}
			Map<String, Object> map = bindingResult.getModel();
			List<FieldError> errors = bindingResult.getFieldErrors();
			return modelAndView;
		} else {
			return new ModelAndView(Constants.View.HOME);
		}
		
	}
}
