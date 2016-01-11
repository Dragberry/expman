package net.dragberry.expman.business;

import net.dragberry.expman.bean.TransactionTO;
import net.dragberry.expman.query.TransactionCreateQuery;
import net.dragberry.expman.query.DeleteTransactionQuery;
import net.dragberry.expman.bean.ResultListTO;
import net.dragberry.expman.bean.ResultTO;

public interface TransactionService {
	
	public static enum Type {
		C, D
	}
	
	ResultTO<TransactionTO> createTransaction(TransactionCreateQuery query);

	ResultListTO<TransactionTO> fetchTransactions();
	
	ResultTO<TransactionTO> deleteTransaction(DeleteTransactionQuery query);
	
}
