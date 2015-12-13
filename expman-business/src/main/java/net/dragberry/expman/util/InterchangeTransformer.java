package net.dragberry.expman.util;

import net.dragberry.expman.bean.CounterPartyTO;
import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.bean.InterchangeTO;
import net.dragberry.expman.bean.InterchangeTypeTO;
import net.dragberry.expman.domain.CounterParty;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Interchange;
import net.dragberry.expman.domain.InterchangeType;

public class InterchangeTransformer implements EntityTransformer<Interchange, InterchangeTO> {

	@Override
	public Interchange transform(InterchangeTO bean) {
		Interchange interchange = new Interchange();
		interchange.setAmount(bean.getAmount());
		interchange.setCurrency(bean.getCurrency());
		interchange.setInterchangeKey(bean.getInterchangeKey());
		interchange.setProcessingDate(bean.getProcessingDate());
		interchange.setDescription(bean.getDescription());
		Customer customer = Transformers.getCustomerTransformer().transform(bean.getCustomer());
		interchange.setCustomer(customer);
		InterchangeType interchangeType = Transformers.getInterchangeTypeTransformer().transform(bean.getInterchangeType());
		interchange.setInterchangeType(interchangeType);
		CounterParty cp = Transformers.getCounterPartyTransformer().transform(bean.getCounterParty());
		interchange.setCounterParty(cp);
		return interchange;
	}

	@Override
	public InterchangeTO transform(Interchange object) {
		InterchangeTO interchangeTO = new InterchangeTO();
		interchangeTO.setAmount(object.getAmount());
		interchangeTO.setCurrency(object.getCurrency());
		interchangeTO.setInterchangeKey(object.getInterchangeKey());
		interchangeTO.setProcessingDate(object.getProcessingDate());
		interchangeTO.setDescription(object.getDescription());
		CustomerTO customerTO = Transformers.getCustomerTransformer().transform(object.getCustomer());
		interchangeTO.setCustomer(customerTO);
		InterchangeTypeTO interchangeTypeTO = Transformers.getInterchangeTypeTransformer().transform(object.getInterchangeType());
		interchangeTO.setInterchangeType(interchangeTypeTO);
		CounterPartyTO cp = Transformers.getCounterPartyTransformer().transform(object.getCounterParty());
		interchangeTO.setCounterParty(cp);
		return interchangeTO;
	}

}
