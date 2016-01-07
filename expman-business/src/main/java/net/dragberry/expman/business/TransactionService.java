package net.dragberry.expman.business;

import net.dragberry.expman.bean.TransactionTO;
import net.dragberry.expman.query.CreateTransactionQuery;
import net.dragberry.expman.query.DeleteTransactionQuery;
import net.dragberry.expman.bean.ResultListTO;
import net.dragberry.expman.bean.ResultTO;

public interface TransactionService {
	
	ResultTO<TransactionTO> createTransaction(CreateTransactionQuery query);

	ResultListTO<TransactionTO> fetchTransactions();
	
	ResultTO<TransactionTO> deleteTransaction(DeleteTransactionQuery query);

}
