package net.dragberry.expman.util;

import net.dragberry.expman.bean.CounterPartyTO;
import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.bean.TransactionTO;
import net.dragberry.expman.bean.TransactionTypeTO;
import net.dragberry.expman.domain.CounterParty;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Transaction;
import net.dragberry.expman.domain.TransactionType;

public class TransactionTransformer implements EntityTransformer<Transaction, TransactionTO> {

	@Override
	public Transaction transform(TransactionTO bean) {
		Transaction transaction = new Transaction();
		transaction.setAmount(bean.getAmount());
		transaction.setCurrency(bean.getCurrency());
		transaction.setTransactionKey(bean.getTransactionKey());
		transaction.setProcessingDate(bean.getProcessingDate());
		transaction.setDescription(bean.getDescription());
//		Customer customer = Transformers.getCustomerTransformer().transform(bean.getCustomer());
//		transaction.setCustomer(customer);
//		TransactionType transactionType = Transformers.getTransactionTypeTransformer().transform(bean.getTransactionType());
//		transaction.setTransactionType(transactionType);
//		CounterParty cp = Transformers.getCounterPartyTransformer().transform(bean.getCounterParty());
//		transaction.setCounterParty(cp);
		return transaction;
	}

	@Override
	public TransactionTO transform(Transaction object) {
		TransactionTO transactionTO = new TransactionTO();
		transactionTO.setAmount(object.getAmount());
		transactionTO.setCurrency(object.getCurrency());
		transactionTO.setTransactionKey(object.getTransactionKey());
		transactionTO.setProcessingDate(object.getProcessingDate());
		transactionTO.setDescription(object.getDescription());
		CustomerTO customerTO = Transformers.getCustomerTransformer().transform(object.getCustomer());
//		transactionTO.setCustomer(customerTO);
		TransactionTypeTO transactionTypeTO = Transformers.getTransactionTypeTransformer().transform(object.getTransactionType());
//		transactionTO.setTransactionType(transactionTypeTO);
		CounterPartyTO cp = Transformers.getCounterPartyTransformer().transform(object.getCounterParty());
//		transactionTO.setCounterParty(cp);
		return transactionTO;
	}

}
