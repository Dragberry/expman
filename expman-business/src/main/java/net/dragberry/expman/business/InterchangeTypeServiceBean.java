package net.dragberry.expman.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.dragberry.expman.bean.InterchangeTypeTO;
import net.dragberry.expman.bean.ResultListTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.InterchangeType;
import net.dragberry.expman.repository.CustomerRepo;
import net.dragberry.expman.repository.InterchangeTypeRepo;
import net.dragberry.expman.util.ResultFactory;
import net.dragberry.expman.util.Transformers;

@Service
@Transactional
public class InterchangeTypeServiceBean implements InterchangeTypeService {

	@Autowired
	private InterchangeTypeRepo interchangeTypeRepo;
	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public ResultTO<InterchangeTypeTO> findInterchangeTypeByKey(Long interchangeKey) {
		InterchangeType interchange = interchangeTypeRepo.findOne(interchangeKey);
		InterchangeTypeTO interchangeTypeTO = Transformers.getInterchangeTypeTransformer().transform(interchange);
		return ResultFactory.createResult(interchangeTypeTO);
	}

	@Override
	public ResultListTO<InterchangeTypeTO> findInterchangeTypeListForCustomer(Long customerKey) {
		Customer customer = customerRepo.findOne(customerKey);
		List<InterchangeType> list = interchangeTypeRepo.findByCustomer(customer);
		List<InterchangeTypeTO> toList = new ArrayList<>();
		for (InterchangeType interchangeType : list) {
			InterchangeTypeTO to = Transformers.getInterchangeTypeTransformer().transform(interchangeType);
			toList.add(to);
		}
		return ResultFactory.createResultList(toList);
	}

}
