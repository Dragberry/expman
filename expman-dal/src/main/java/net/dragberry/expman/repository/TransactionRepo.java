package net.dragberry.expman.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Transaction;
import net.dragberry.expman.dto.TransactionDTO;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

	String TRANSACTION_LIST_QUERY = "SELECT new net.dragberry.expman.dto.TransactionDTO(t.transactionKey, t.customer.customerKey, a.accountKey, a.number, cp.counterPartyKey, cp.name, tt.transactionTypeKey, t.type, tt.name, t.description, t.processingDate, t.amount, t.currency) FROM  Transaction t, TransactionType tt, CounterParty cp, Account a  WHERE t.transactionType.transactionTypeKey = tt.transactionTypeKey AND t.account.accountKey = a.accountKey AND t.counterParty.counterPartyKey = cp.counterPartyKey AND t.customer = :customer ORDER BY t.processingDate";
	
	@Query(TRANSACTION_LIST_QUERY)
	Page<TransactionDTO> fetchTransactionList(@Param("customer")Customer customerKey, Pageable pageable);
	
}
