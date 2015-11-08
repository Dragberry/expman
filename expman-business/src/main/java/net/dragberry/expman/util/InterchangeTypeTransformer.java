package net.dragberry.expman.util;

import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.bean.InterchangeTypeTO;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.InterchangeType;

public class InterchangeTypeTransformer implements EntityTransformer<InterchangeType, InterchangeTypeTO> {

	@Override
	public InterchangeType transform(InterchangeTypeTO bean) {
		InterchangeType interchangeType = new InterchangeType();
		interchangeType.setInterchangeKey(bean.getInterchangeTypeKey());
		interchangeType.setName(bean.getName());
		interchangeType.setType(bean.getType());
		Customer customer = Transformers.getCustomerTransformer().transform(bean.getCustomer());
		interchangeType.setCustomer(customer);
		return interchangeType;
	}

	@Override
	public InterchangeTypeTO transform(InterchangeType object) {
		InterchangeTypeTO interchangeTypeTO = new InterchangeTypeTO();
		interchangeTypeTO.setInterchangeTypeKey(object.getInterchangeKey());;
		interchangeTypeTO.setName(object.getName());
		interchangeTypeTO.setType(object.getType());
		CustomerTO customerTO = Transformers.getCustomerTransformer().transform(object.getCustomer());
		interchangeTypeTO.setCustomer(customerTO);
		return interchangeTypeTO;
	}

}
