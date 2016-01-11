package net.dragberry.expman.business;

import net.dragberry.expman.bean.CounterPartyTO;
import net.dragberry.expman.bean.ResultListTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.query.CounterPartyCreateQuery;

public interface CounterPartyService {
	
	ResultTO<CounterPartyTO> createCounterParty(CounterPartyCreateQuery query);
	
	ResultListTO<CounterPartyTO> fetchCounterPartyList(Long customerKey);
	
	ResultTO<CounterPartyTO> findCounterParty(Long counterPartyKey);

}
