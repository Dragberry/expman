package net.dragberry.expman.util;

import net.dragberry.expman.bean.CounterPartyTO;
import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.bean.TransactionTO;
import net.dragberry.expman.bean.TransactionTypeTO;
import net.dragberry.expman.domain.CounterParty;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Transaction;
import net.dragberry.expman.domain.TransactionType;

public final class Transformers {
	
	private static final EntityTransformer<Transaction, TransactionTO> transactionTransformer = new TransactionTransformer();
	private static final EntityTransformer<Customer, CustomerTO> customerTransformer = new CustomerTransformer();
	private static final EntityTransformer<TransactionType, TransactionTypeTO> transactionTypeTransformer = new TransactionTypeTransformer();
	private static final EntityTransformer<CounterParty, CounterPartyTO> counterPartyTransformer = new CounterPartyTransformer();
	
	private Transformers() {
	}
	
	public static EntityTransformer<TransactionType, TransactionTypeTO> getTransactionTypeTransformer() {
		return transactionTypeTransformer;
	}
	
	public static EntityTransformer<Customer, CustomerTO> getCustomerTransformer() {
		return customerTransformer;
	}
	
	public static EntityTransformer<Transaction, TransactionTO> getTransactionTransformer() {
		return transactionTransformer;
	}
	
	public static EntityTransformer<CounterParty, CounterPartyTO> getCounterPartyTransformer() {
		return counterPartyTransformer;
	}
}
