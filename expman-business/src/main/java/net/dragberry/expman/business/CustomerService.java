package net.dragberry.expman.business;

import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.query.CustomerCreateQuery;

public interface CustomerService {
	
	ResultTO<CustomerTO> findByCustomerName(String customerName);
	
	ResultTO<CustomerTO> createCustomer(CustomerCreateQuery customerQuery);

}
