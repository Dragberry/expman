package net.dragberry.expman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.dragberry.expman.domain.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

	@Query("select i.transactionKey, it.transactionTypeKey from Transaction i, TransactionType it where it.transactionTypeKey = i.transactionType")
	List<Object> fetchTransactionList();
	
}
