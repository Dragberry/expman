package net.dragberry.expman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.dragberry.expman.domain.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	
}
