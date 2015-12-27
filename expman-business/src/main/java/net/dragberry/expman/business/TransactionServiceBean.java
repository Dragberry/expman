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
import net.dragberry.expman.domain.Transaction;
import net.dragberry.expman.query.DeleteTransactionQuery;
import net.dragberry.expman.repository.TransactionRepo;
import net.dragberry.expman.util.IssueFactory;
import net.dragberry.expman.util.ResultFactory;
import net.dragberry.expman.util.Transformers;

@Service
@Transactional
public class TransactionServiceBean implements TransactionService {

	@Autowired
	private TransactionRepo transactionRepo;

	@Override
	public ResultTO<TransactionTO> createTransaction(TransactionTO transactionTO) {
		Transaction transaction = Transformers.getTransactionTransformer().transform(transactionTO);
		transaction = transactionRepo.save(transaction);
		TransactionTO createdTransactionTO = Transformers.getTransactionTransformer().transform(transaction);
		return ResultFactory.createResult(createdTransactionTO);
	}

	@Override
	public ResultListTO<TransactionTO> fetchTransactions() {
		List<Transaction> list = transactionRepo.findAll();
		List<Object> list2 = transactionRepo.fetchTransactionList();
		List<TransactionTO> listTO = new ArrayList<>();
		for (Transaction transaction : list) {
			TransactionTO transactionTO = Transformers.getTransactionTransformer().transform(transaction);
			listTO.add(transactionTO);
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
