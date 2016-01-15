package net.dragberry.expman.util;

import net.dragberry.expman.bean.TransactionTO;
import net.dragberry.expman.domain.Transaction;

public class TransactionTransformer implements EntityTransformer<Transaction, TransactionTO> {

	@Override
	public Transaction transform(TransactionTO bean) {
		Transaction transaction = new Transaction();
		transaction.setAmount(bean.getAmount());
		transaction.setCurrency(bean.getCurrency());
		transaction.setTransactionKey(bean.getTransactionKey());
		transaction.setProcessingDate(bean.getProcessingDate());
		transaction.setDescription(bean.getDescription());
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
		return transactionTO;
	}

}
