package net.dragberry.expman.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.dragberry.expman.bean.AccountBalanceTO;
import net.dragberry.expman.bean.AccountTO;
import net.dragberry.expman.bean.ResultListTO;
import net.dragberry.expman.domain.Account;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.query.AccountBalanceQuery;
import net.dragberry.expman.query.AccountQuery;
import net.dragberry.expman.repository.AccountRepo;
import net.dragberry.expman.repository.CustomerRepo;
import net.dragberry.expman.util.ResultFactory;

@Service
public class AccountServiceBean implements AccountService {

	private static final long serialVersionUID = 1851261846227931866L;
	
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public ResultListTO<AccountBalanceTO> fetchAccountBalances(AccountBalanceQuery query) {
		Customer customer = customerRepo.findOne(query.getCustomerKey());
		List<Account> customerAccounts = accountRepo.findByCustomer(customer); 
		List<AccountBalanceTO> accountBalanceList = new ArrayList<>();
		for (Account account : customerAccounts) {
			AccountBalanceTO abTO = new AccountBalanceTO();
			abTO.setCurrency(account.getCurrency());
			abTO.setNumber(account.getNumber());
			abTO.setType(account.getType().toString());
			BigDecimal balance = accountRepo.calculateAccountBalance(account.getAccountKey());
			abTO.setBalance(balance);
			accountBalanceList.add(abTO);
		}
		return ResultFactory.createResultList(accountBalanceList);
	}

	@Override
	public ResultListTO<AccountTO> fetchAccounts(AccountQuery accountQuery) {
		Customer customer = customerRepo.findOne(accountQuery.getCustomerKey());
		List<Account> customerAccounts = accountRepo.findByCustomer(customer);
		List<AccountTO> listTO = new ArrayList<>();
		for (Account account : customerAccounts) {
			AccountTO acTO = new AccountTO();
			acTO.setAccountKey(account.getAccountKey());
			acTO.setNumber(account.getNumber());
			listTO.add(acTO);
		}
		return ResultFactory.createResultList(listTO);
	}

}
