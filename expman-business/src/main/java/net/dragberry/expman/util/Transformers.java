package net.dragberry.expman.util;

import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.bean.InterchangeTO;
import net.dragberry.expman.bean.InterchangeTypeTO;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Interchange;
import net.dragberry.expman.domain.InterchangeType;

public final class Transformers {
	
	private static final EntityTransformer<Interchange, InterchangeTO> interchangeTransformer = new InterchangeTransformer();
	private static final EntityTransformer<Customer, CustomerTO> customerTransformer = new CustomerTransformer();
	private static final EntityTransformer<InterchangeType, InterchangeTypeTO> interchangeTypeTransformer = new InterchangeTypeTransformer();
	
	private Transformers() {
	}
	
	public static EntityTransformer<InterchangeType, InterchangeTypeTO> getInterchangeTypeTransformer() {
		return interchangeTypeTransformer;
	}
	
	public static EntityTransformer<Customer, CustomerTO> getCustomerTransformer() {
		return customerTransformer;
	}
	
	public static EntityTransformer<Interchange, InterchangeTO> getInterchangeTransformer() {
		return interchangeTransformer;
	}

}
