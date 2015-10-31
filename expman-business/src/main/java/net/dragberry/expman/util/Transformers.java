package net.dragberry.expman.util;

import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.domain.Customer;

public final class Transformers {
	
	private static final EntityTransformer<Customer, CustomerTO> customerTransformer = new CustomerTransformer();

	private Transformers() {
	}
	
	public static EntityTransformer<Customer, CustomerTO> getCustomerTransformer() {
		return customerTransformer;
	}

}
