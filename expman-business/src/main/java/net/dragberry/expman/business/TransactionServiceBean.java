package net.dragberry.expman.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import net.dragberry.expman.query.CreateTransactionQuery;
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
	public ResultTO<TransactionTO> createTransaction(CreateTransactionQuery query) {
		Transaction tr = new Transaction();
		tr.setTransactionKey(query.getTransactionKey());
		tr.setAmount(query.getAmount());
		tr.setDescription(query.getDescription());
		tr.setCurrency(query.getCurrency());
		tr.setProcessingDate(query.getProcessingDate());
		
		Customer customer = customerRepo.findOne(query.getCustomerKey());
		tr.setCustomer(customer);
		
		TransactionType type = transactionTypeRepo.findOne(query.getTransactionTypeKey());
		tr.setTransactionType(type);
		
		CounterParty cp = counterPartyRepo.findOne(query.getCounterPartyKey());
		tr.setCounterParty(cp);
		
		Account account = accountRepo.findOne(query.getAccountKey());
		tr.setAccount(account);
		
		tr = transactionRepo.save(tr);
		TransactionTO createdTransactionTO = Transformers.getTransactionTransformer().transform(tr);
		return ResultFactory.createResult(createdTransactionTO);
	}

	@Override
	public ResultListTO<TransactionTO> fetchTransactions() {
		Customer c = customerRepo.findOne(3L);
		List<TransactionDTO> list = transactionRepo.fetchTransactionList(c);
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
