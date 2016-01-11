package net.dragberry.expman.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.dragberry.expman.bean.TransactionTO;
import net.dragberry.expman.bean.IssueTO;
import net.dragberry.expman.bean.ResultListTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.domain.Account;
import net.dragberry.expman.domain.CounterParty;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Transaction;
import net.dragberry.expman.domain.TransactionType;
import net.dragberry.expman.dto.TransactionDTO;
import net.dragberry.expman.messages.BusinessMessageCodes;
import net.dragberry.expman.query.TransactionCreateQuery;
import net.dragberry.expman.query.TransactionListQuery;
import net.dragberry.expman.query.DeleteTransactionQuery;
import net.dragberry.expman.repository.AccountRepo;
import net.dragberry.expman.repository.CounterPartyRepo;
import net.dragberry.expman.repository.CustomerRepo;
import net.dragberry.expman.repository.TransactionRepo;
import net.dragberry.expman.repository.TransactionTypeRepo;
import net.dragberry.expman.util.IssueFactory;
import net.dragberry.expman.util.ResultFactory;
import net.dragberry.expman.util.Transformers;

@Service
@Transactional
public class TransactionServiceBean implements TransactionService {

	@Autowired
	private TransactionRepo transactionRepo;
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private TransactionTypeRepo transactionTypeRepo;
	@Autowired
	private CounterPartyRepo counterPartyRepo;
	@Autowired
	private AccountRepo accountRepo;

	@Override
	public ResultTO<TransactionTO> createTransaction(TransactionCreateQuery query) {
		List<IssueTO> issues = new ArrayList<>();
		Transaction tr = new Transaction();
		tr.setTransactionKey(query.getTransactionKey());
		if (query.getAmount() == null) {
			issues.add(IssueFactory.createIssue(BusinessMessageCodes.CreateTransaction.AMOUNT_IS_MANDATORY,  BusinessMessageCodes.DOMAIN));
		} else if (query.getAmount().signum() == -1) {
			issues.add(IssueFactory.createIssue(BusinessMessageCodes.CreateTransaction.AMOUNT_IS_INCORRECT,  BusinessMessageCodes.DOMAIN));
		}
		tr.setAmount(query.getAmount());
		
		if (StringUtils.isBlank(query.getDescription())) {
			issues.add(IssueFactory.createIssue(BusinessMessageCodes.CreateTransaction.DESCRIPTION_IS_MANDATORY,  BusinessMessageCodes.DOMAIN));
		} else if (query.getDescription().length() > 255) {
			issues.add(IssueFactory.createIssue(BusinessMessageCodes.CreateTransaction.DESCRIPTION_IS_TOO_LARGE,  BusinessMessageCodes.DOMAIN));
		}
		tr.setDescription(query.getDescription());
		
		tr.setProcessingDate(query.getProcessingDate());
		
		Customer customer = customerRepo.findOne(query.getCustomerKey());
		tr.setCustomer(customer);
		
		TransactionType type = transactionTypeRepo.findOne(query.getTransactionTypeKey());
		tr.setTransactionType(type);
		
		CounterParty cp = counterPartyRepo.findOne(query.getCounterPartyKey());
		tr.setCounterParty(cp);
		
		Account account = accountRepo.findOne(query.getAccountKey());
		tr.setAccount(account);
		
		if (!account.getCurrency().equals(query.getCurrency())) {
			issues.add(IssueFactory.createIssue(BusinessMessageCodes.CreateTransaction.CURRENCY_IS_NOT_MATCH_WITH_ACCOUNT_CURRENCY,  BusinessMessageCodes.DOMAIN));
		}
		tr.setCurrency(query.getCurrency());
		
		tr.setType(query.getType());
		
		if (issues.isEmpty()) {
			tr = transactionRepo.save(tr);
		}
		TransactionTO createdTransactionTO = Transformers.getTransactionTransformer().transform(tr);
		return ResultFactory.createResult(createdTransactionTO, issues);
	}

	@Override
	public ResultListTO<TransactionTO> fetchTransactions(TransactionListQuery query) {
		Customer c = customerRepo.findOne(query.getCustomerKey());
		PageRequest pageRequest = new PageRequest(query.getPageNumber(), query.getPageSize());
		Page<TransactionDTO> list = transactionRepo.fetchTransactionList(c, pageRequest);
		List<TransactionTO> listTO = new ArrayList<>();
		for (TransactionDTO tr : list) {
			TransactionTO to = new TransactionTO();
			to.setAccountKey(tr.getAccountKey());
			to.setAccountNumber(tr.getAccountNumber());
			to.setAmount(tr.getAmount());
			to.setCounterPartyKey(tr.getCounterPartyKey());
			to.setCounterPartyName(tr.getCounterPartyName());
			to.setCurrency(tr.getCurrency());
			to.setCustomerKey(tr.getCustomerKey());
			to.setDescription(tr.getDescription());
			to.setProcessingDate(tr.getProcessingDate());
			to.setTransactionKey(tr.getTransactionKey());
			to.setTransactionType(tr.getTransactionType());
			to.setTransactionTypeKey(tr.getTransactionTypeKey());
			to.setTransactionTypeName(tr.getTransactionTypeName());
			listTO.add(to);
		}
		return ResultFactory.createResultList(listTO);
	}

	@Override
	public ResultTO<TransactionTO> deleteTransaction(DeleteTransactionQuery query) {
		TransactionTO transactionTO = new TransactionTO();
		Transaction transactionToDelete = transactionRepo.findOne(query.getTransactionKey());
		List<IssueTO> issues = new ArrayList<>();
		if (transactionToDelete == null) {
			IssueTO issue = IssueFactory.createIssue("000100", "Transaction");
			issues.add(issue);
		} else {
			transactionTO = Transformers.getTransactionTransformer().transform(transactionToDelete);
		}
		if (transactionToDelete.getCustomer().getCustomerKey() != query.getCustomerKey()) {
			IssueTO issue = IssueFactory.createIssue("000101", "Transaction");
			issues.add(issue);
		}
		if (issues.isEmpty()) {
			transactionRepo.delete(query.getTransactionKey());
		}
		return ResultFactory.createResult(transactionTO, issues);
	}

	
	
}
