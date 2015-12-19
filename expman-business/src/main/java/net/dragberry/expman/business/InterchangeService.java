package net.dragberry.expman.business;

import net.dragberry.expman.bean.InterchangeTO;
import net.dragberry.expman.bean.ResultListTO;
import net.dragberry.expman.bean.ResultTO;

public interface InterchangeService {
	
	ResultTO<InterchangeTO> createInterchange(InterchangeTO interchangeTO);

	ResultListTO<InterchangeTO> fetchInterchanges();

}
