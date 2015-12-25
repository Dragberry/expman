package net.dragberry.expman.util;

import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.bean.TransactionTypeTO;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.TransactionType;

public class TransactionTypeTransformer implements EntityTransformer<TransactionType, TransactionTypeTO> {

	@Override
	public TransactionType transform(TransactionTypeTO bean) {
		TransactionType transactionType = new TransactionType();
		transactionType.setTransactionTypeKey(bean.getTransactionTypeKey());
		transactionType.setName(bean.getName());
		transactionType.setType(bean.getType());
		Customer customer = Transformers.getCustomerTransformer().transform(bean.getCustomer());
		transactionType.setCustomer(customer);
		return transactionType;
	}

	@Override
	public TransactionTypeTO transform(TransactionType object) {
		TransactionTypeTO transactionTypeTO = new TransactionTypeTO();
		transactionTypeTO.setTransactionTypeKey(object.getTransactionTypeKey());;
		transactionTypeTO.setName(object.getName());
		transactionTypeTO.setType(object.getType());
		CustomerTO customerTO = Transformers.getCustomerTransformer().transform(object.getCustomer());
		transactionTypeTO.setCustomer(customerTO);
		return transactionTypeTO;
	}

}
