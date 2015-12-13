package net.dragberry.expman.business;

import net.dragberry.expman.bean.InterchangeTypeTO;
import net.dragberry.expman.bean.ResultListTO;
import net.dragberry.expman.bean.ResultTO;

public interface InterchangeTypeService {
	
	ResultTO<InterchangeTypeTO> findInterchangeTypeByKey(Long interchangeKey);

	ResultListTO<InterchangeTypeTO> findInterchangeTypeListForCustomer(Long customerKey);

	ResultTO<InterchangeTypeTO> createInterchangeType(InterchangeTypeTO interchangeTypeTO);
}
