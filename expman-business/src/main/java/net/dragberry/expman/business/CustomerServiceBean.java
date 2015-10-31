package net.dragberry.expman.business;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Role;
import net.dragberry.expman.repository.CustomerRepo;
import net.dragberry.expman.repository.RoleRepo;
import net.dragberry.expman.util.Transformers;

@Service
@Transactional
public class CustomerServiceBean implements CustomerService {
	
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public CustomerTO findByCustomerName(String customerName) {
		Customer customer = customerRepo.findByCustomerName(customerName);
		return Transformers.getCustomerTransformer().transform(customer);
	}

	@Override
	public CustomerTO createCustomer(CustomerTO customerTO) {
		Customer customer = Transformers.getCustomerTransformer().transform(customerTO);
		Set<Role> roles = roleRepo.findByRoleNameIn(customerTO.getRoles());
		customer.setRoles(roles);
		customer = customerRepo.save(customer);
		return Transformers.getCustomerTransformer().transform(customer);
	}
	
	

}
