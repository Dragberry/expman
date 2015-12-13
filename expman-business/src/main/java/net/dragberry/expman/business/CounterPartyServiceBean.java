package net.dragberry.expman.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.dragberry.expman.bean.CounterPartyTO;
import net.dragberry.expman.bean.ResultListTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.domain.CounterParty;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.repository.CounterPartyRepo;
import net.dragberry.expman.repository.CustomerRepo;
import net.dragberry.expman.util.ResultFactory;
import net.dragberry.expman.util.Transformers;

@Service
@Transactional
public class CounterPartyServiceBean implements CounterPartyService {
	
	@Autowired
	private CounterPartyRepo counterPartyRepo;
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public ResultTO<CounterPartyTO> createCounterParty(CounterPartyTO counterPartyTO) {
		CounterParty cp = Transformers.getCounterPartyTransformer().transform(counterPartyTO);
		cp = counterPartyRepo.save(cp);
		CounterPartyTO cpTO = Transformers.getCounterPartyTransformer().transform(cp);
		return ResultFactory.createResult(cpTO);
	}

	@Override
	public ResultListTO<CounterPartyTO> fetchCounterPartyList(Long customerKey) {
		Customer customer = customerRepo.findOne(customerKey);
		List<CounterParty> list = counterPartyRepo.findByCustomer(customer);
		List<CounterPartyTO> listTO = new ArrayList<>();
		for (CounterParty cp : list) {
			CounterPartyTO cpTO = Transformers.getCounterPartyTransformer().transform(cp);
			listTO.add(cpTO);
		}
		return ResultFactory.createResultList(listTO);
	}

	@Override
	public ResultTO<CounterPartyTO> findCounterParty(Long counterPartyKey) {
		CounterParty cp = counterPartyRepo.findOne(counterPartyKey);
		CounterPartyTO cpTO = Transformers.getCounterPartyTransformer().transform(cp);
		return ResultFactory.createResult(cpTO);
	}

}
