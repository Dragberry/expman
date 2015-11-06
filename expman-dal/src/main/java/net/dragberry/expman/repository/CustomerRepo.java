package net.dragberry.expman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.dragberry.expman.domain.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	Customer findByCustomerName(String customerName);
	
	@Query(value = "SELECT COUNT(c) > 0 FROM Customer c WHERE c.customerName = :customerName")
	boolean ifCustomerNameExist(@Param("customerName")String customerName);
	
	@Query(value = "SELECT COUNT(c) > 0 FROM Customer c WHERE c.email = :email")
	boolean ifCustomerEmailExist(@Param("email")String email);
}
