package net.dragberry.expman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.InterchangeType;

public interface InterchangeTypeRepo extends JpaRepository<InterchangeType, Long> {

	List<InterchangeType> findByCustomer(Customer customer);
}
