package net.dragberry.expman.business;

import net.dragberry.expman.bean.TransactionTypeTO;
import net.dragberry.expman.bean.ResultListTO;
import net.dragberry.expman.bean.ResultTO;

public interface TransactionTypeService {
	
	ResultTO<TransactionTypeTO> findTransactionTypeByKey(Long transactionKey);

	ResultListTO<TransactionTypeTO> findTransactionTypeListForCustomer(Long customerKey);

	ResultTO<TransactionTypeTO> createTransactionType(TransactionTypeTO transactionTypeTO);
}
