package net.dragberry.expman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.TransactionType;

public interface TransactionTypeRepo extends JpaRepository<TransactionType, Long> {

	List<TransactionType> findByCustomer(Customer customer);
}
