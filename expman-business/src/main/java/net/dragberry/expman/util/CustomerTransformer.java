package net.dragberry.expman.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.StandardPasswordEncoder;

import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Role;

public class CustomerTransformer implements EntityTransformer<Customer, CustomerTO> {
	
	private static final CharSequence SHA_256_ALGORITHM = "SHA-256";
	
	private final StandardPasswordEncoder encoder = new StandardPasswordEncoder(SHA_256_ALGORITHM);

	@Override
	public Customer transform(CustomerTO bean) {
		Customer customer = new Customer();
		customer.setCustomerKey(bean.getCustomerKey());
		customer.setCustomerName(bean.getCustomerName());
		customer.setPassword(encoder.encode(bean.getPassword()));
		customer.setEnabled(bean.isEnabled());
		return customer;
	}

	@Override
	public CustomerTO transform(Customer object) {
		if (object == null) {
			return null;
		}
		CustomerTO customerTO = new CustomerTO();
		customerTO.setCustomerKey(object.getCustomerKey());
		customerTO.setCustomerName(object.getCustomerName());
		customerTO.setPassword(object.getPassword());
		customerTO.setEnabled(object.getEnabled());
		Set<String> roles = new HashSet<>();
		for (Role role : object.getRoles()) {
			roles.add(role.getRoleName());
		}
		customerTO.setRoles(roles);
		return customerTO;
	}

}
