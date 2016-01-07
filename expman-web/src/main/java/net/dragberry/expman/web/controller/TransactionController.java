package net.dragberry.expman.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.dragberry.expman.bean.AccountTO;
import net.dragberry.expman.bean.CounterPartyTO;
import net.dragberry.expman.bean.IssueTO;
import net.dragberry.expman.bean.TransactionTO;
import net.dragberry.expman.bean.TransactionTypeTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.business.AccountService;
import net.dragberry.expman.business.CounterPartyService;
import net.dragberry.expman.business.ReferenceService;
import net.dragberry.expman.business.TransactionService;
import net.dragberry.expman.business.TransactionTypeService;
import net.dragberry.expman.messages.BusinessMessageCodes;
import net.dragberry.expman.query.AccountQuery;
import net.dragberry.expman.query.CreateTransactionQuery;
import net.dragberry.expman.query.DeleteTransactionQuery;
import net.dragberry.expman.web.common.Constants;
import net.dragberry.expman.web.security.ExpmanSecurityContext;

@Controller
public class TransactionController implements Serializable {

	private static final String CURRENCY_LIST = "currencyList";

	private static final String ACCOUNT_LIST = "accountList";

	private static final String TRANSACTION = "transaction";

	private static final String COUNTER_PARTY_LIST = "counterPartyList";

	private static final String TRANSACTION_TYPE_LIST = "transactionTypeList";

	private static final long serialVersionUID = 4920765963061089750L;

	private static final Logger LOG = LogManager.getLogger(TransactionController.class.getName());
	
	private static final String TRANSACTION_LIST = "transactionList";
	
	private static final Map<String, String> errorMap = new HashMap<>();
	
	static {
		errorMap.put(BusinessMessageCodes.CreateTransaction.AMOUNT_IS_INCORRECT, "amount");
		errorMap.put(BusinessMessageCodes.CreateTransaction.AMOUNT_IS_MANDATORY, "amount");
		errorMap.put(BusinessMessageCodes.CreateTransaction.DESCRIPTION_IS_MANDATORY, "description");
		errorMap.put(BusinessMessageCodes.CreateTransaction.DESCRIPTION_IS_TOO_LARGE, "description");
		errorMap.put(BusinessMessageCodes.CreateTransaction.CURRENCY_IS_NOT_MATCH_WITH_ACCOUNT_CURRENCY, "currency");
	}

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private TransactionTypeService transactionTypeService;
	@Autowired
	private CounterPartyService counterPartyService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private ReferenceService referenceService;
	
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
		ModelAndView modelAndView = prepareCreateTransactionScreen(new CreateTransactionQuery());
		return modelAndView;
	}

	@RequestMapping(value = Constants.Path.TRANSACTION_CREATE, method = RequestMethod.POST)
	public ModelAndView createTransaction(@ModelAttribute(TRANSACTION)@Valid CreateTransactionQuery transaction, BindingResult bindingResult, HttpServletRequest request) {
		transaction.setCustomerKey(ExpmanSecurityContext.getCustomerKey());
		ResultTO<TransactionTO> result = transactionService.createTransaction(transaction);
		if (result.hasIssues()) {
			ModelAndView modelAndView = prepareCreateTransactionScreen(transaction);
			for (IssueTO issue : result.getIssueLog()) {
				String field = errorMap.get(issue.getIssueCode());
				if (field != null) {
					bindingResult.rejectValue(field, issue.getIssueCode());
				}
			}
			modelAndView.addAllObjects(bindingResult.getModel());
			return modelAndView;
		} else {
			return new ModelAndView(Constants.View.HOME);
		}
	}
	
	private ModelAndView prepareCreateTransactionScreen(CreateTransactionQuery transaction) {
		ModelAndView modelAndView = new ModelAndView(Constants.View.TRANSACTION_CREATE);
		modelAndView.addObject(TRANSACTION, transaction);
		Long loggedCutomerKey = ExpmanSecurityContext.getCustomerKey();
		
		List<TransactionTypeTO> transactionTypeList = transactionTypeService.findTransactionTypeListForCustomer(loggedCutomerKey).getList();
		modelAndView.addObject(TRANSACTION_TYPE_LIST, transactionTypeList);
		
		List<CounterPartyTO> counterPartyList = counterPartyService.fetchCounterPartyList(loggedCutomerKey).getList();
		modelAndView.addObject(COUNTER_PARTY_LIST, counterPartyList);
		
		List<String> currencyList = referenceService.fecthCurrecnyList();
		modelAndView.addObject(CURRENCY_LIST, currencyList);
		
		AccountQuery accountQuery = new AccountQuery();
		accountQuery.setCustomerKey(loggedCutomerKey);
		List<AccountTO> accountList = accountService.fetchAccounts(accountQuery).getList();
		modelAndView.addObject(ACCOUNT_LIST, accountList);
		
		return modelAndView;
	}
}
