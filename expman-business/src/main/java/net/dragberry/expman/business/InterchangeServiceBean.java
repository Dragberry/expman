package net.dragberry.expman.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.dragberry.expman.bean.InterchangeTO;
import net.dragberry.expman.bean.ResultListTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.domain.Interchange;
import net.dragberry.expman.repository.InterchangeRepo;
import net.dragberry.expman.util.ResultFactory;
import net.dragberry.expman.util.Transformers;

@Service
@Transactional
public class InterchangeServiceBean implements InterchangeService {

	@Autowired
	private InterchangeRepo interchangeRepo;

	@Override
	public ResultTO<InterchangeTO> createInterchange(InterchangeTO interchangeTO) {
		Interchange interchange = Transformers.getInterchangeTransformer().transform(interchangeTO);
		interchange = interchangeRepo.save(interchange);
		InterchangeTO createdInterchangeTO = Transformers.getInterchangeTransformer().transform(interchange);
		return ResultFactory.createResult(createdInterchangeTO);
	}

	@Override
	public ResultListTO<InterchangeTO> fetchInterchanges() {
		List<Interchange> list = interchangeRepo.findAll();
		List<Object> list2 = interchangeRepo.fetchInterchangeList();
		List<InterchangeTO> listTO = new ArrayList<>();
		for (Interchange interchange : list) {
			InterchangeTO interchangeTO = Transformers.getInterchangeTransformer().transform(interchange);
			listTO.add(interchangeTO);
		}
		return ResultFactory.createResultList(listTO);
	}
	
	
}
