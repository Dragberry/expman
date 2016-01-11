package net.dragberry.expman.web.controller;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.dragberry.expman.bean.AccountBalanceTO;
import net.dragberry.expman.bean.ResultListTO;
import net.dragberry.expman.business.AccountService;
import net.dragberry.expman.query.AccountBalanceQuery;
import net.dragberry.expman.web.common.Constants;
import net.dragberry.expman.web.security.ExpmanSecurityContext;
import net.dragberry.expman.web.session.AccountSession;

@Controller
public class AccountController implements Serializable {

	private static final String ACCOUNT_LIST = "accountList";

	private static final long serialVersionUID = 8887853545591915247L;
	
	@Autowired
	private AccountSession accountSession;
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = Constants.Path.ACCOUNT_LIST)
	public ModelAndView accountList() {
		ModelAndView modelAndView = new ModelAndView(Constants.View.ACCOUNT_LIST);
		if (!accountSession.isInitialized()) {
			AccountBalanceQuery query = new AccountBalanceQuery();
			query.setCustomerKey(ExpmanSecurityContext.getCustomerKey());
			ResultListTO<AccountBalanceTO> accountBalanceList = accountService.fetchAccountBalances(query);
			accountSession.setAccountBalanceList(accountBalanceList.getList());
			accountSession.setInitialized(true);
		}
		modelAndView.addObject(ACCOUNT_LIST, accountSession.getAccountBalanceList());
		return modelAndView;
	}

}
