package net.dragberry.expman.business;

import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.bean.ResultTO;

public interface CustomerService {
	
	ResultTO<CustomerTO> findByCustomerName(String customerName);
	
	ResultTO<CustomerTO> createCustomer(CustomerTO customerTO);

}
