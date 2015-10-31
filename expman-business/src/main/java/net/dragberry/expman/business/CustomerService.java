package net.dragberry.expman.business;

import net.dragberry.expman.bean.CustomerTO;

public interface CustomerService {
	
	CustomerTO findByCustomerName(String customerName);
	
	CustomerTO createCustomer(CustomerTO customerTO);

}
