package net.dragberry.expman.business;

import net.dragberry.expman.bean.TransactionTO;
import net.dragberry.expman.bean.ResultListTO;
import net.dragberry.expman.bean.ResultTO;

public interface TransactionService {
	
	ResultTO<TransactionTO> createTransaction(TransactionTO transactionTO);

	ResultListTO<TransactionTO> fetchTransactions();

}
