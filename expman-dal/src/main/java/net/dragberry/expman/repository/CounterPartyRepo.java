package net.dragberry.expman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.dragberry.expman.domain.CounterParty;
import net.dragberry.expman.domain.Customer;

@Repository
public interface CounterPartyRepo extends JpaRepository<CounterParty, Long> {
	
	List<CounterParty> findByCustomer(Customer customer);
	
	CounterParty findByName(String name);
}
