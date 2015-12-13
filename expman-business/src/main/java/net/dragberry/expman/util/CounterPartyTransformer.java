package net.dragberry.expman.util;

import net.dragberry.expman.bean.CounterPartyTO;
import net.dragberry.expman.domain.CounterParty;

public class CounterPartyTransformer implements EntityTransformer<CounterParty, CounterPartyTO> {

	@Override
	public CounterParty transform(CounterPartyTO bean) {
		CounterParty cp = new CounterParty();
		cp.setCounterPartyKey(bean.getCounterPartyKey());
		cp.setName(bean.getName());
		cp.setCustomer(Transformers.getCustomerTransformer().transform(bean.getCustomer()));
		return cp;
	}

	@Override
	public CounterPartyTO transform(CounterParty object) {
		CounterPartyTO cp = new CounterPartyTO();
		cp.setCounterPartyKey(object.getCounterPartyKey());
		cp.setName(object.getName());
		cp.setCustomer(Transformers.getCustomerTransformer().transform(object.getCustomer()));
		return cp;
	}

}
