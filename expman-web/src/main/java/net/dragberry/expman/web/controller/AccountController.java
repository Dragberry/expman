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

@Controller
public class AccountController implements Serializable {

	private static final long serialVersionUID = 8887853545591915247L;
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = Constants.Path.ACCOUNT_LIST)
	public ModelAndView accountList() {
		ModelAndView modelAndView = new ModelAndView(Constants.View.ACCOUNT_LIST);
		AccountBalanceQuery query = new AccountBalanceQuery();
		query.setCustomerKey(ExpmanSecurityContext.getCustomerKey());
		ResultListTO<AccountBalanceTO> accountBalanceList = accountService.fetchAccountBalances(query);
		if (!accountBalanceList.hasIssues()) {
			modelAndView.addObject("accountList", accountBalanceList.getList());
		} else {
			modelAndView.addObject("accountList", new ArrayList<AccountBalanceTO>());
		}
		return modelAndView;
	}

}
