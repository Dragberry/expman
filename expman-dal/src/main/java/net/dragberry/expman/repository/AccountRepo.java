package net.dragberry.expman.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.dragberry.expman.domain.Account;
import net.dragberry.expman.domain.Customer;

public interface AccountRepo extends JpaRepository<Account, Long> {
	
	String ACCOUNT_BALANCE_QUERY = "SELECT SUM(CASE WHEN TT.TYPE = 'C' THEN -T.AMOUNT ELSE T.AMOUNT END) AS BALANCE"
			+ " FROM TRANSACTION T, TRANSACTION_TYPE TT" 
			+ " WHERE T.TRANSACTION_TYPE_KEY = TT.TRANSACTION_TYPE_KEY"
			+ " AND T.ACCOUNT_KEY = ?1";

	List<Account> findByCustomer(Customer customer);

	@Query(value = ACCOUNT_BALANCE_QUERY, nativeQuery = true)
	BigDecimal calculateAccountBalance(Long accountKey);

}
