package net.dragberry.expman.business;

import net.dragberry.expman.bean.CounterPartyTO;
import net.dragberry.expman.bean.ResultListTO;
import net.dragberry.expman.bean.ResultTO;

public interface CounterPartyService {
	
	ResultTO<CounterPartyTO> createCounterParty(CounterPartyTO counterPartyTO);
	
	ResultListTO<CounterPartyTO> fetchCounterPartyList(Long customerKey);
	
	ResultTO<CounterPartyTO> findCounterParty(Long counterPartyKey);

}
