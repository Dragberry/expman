package net.dragberry.expman.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.dragberry.expman.bean.TransactionTypeTO;
import net.dragberry.expman.bean.ResultListTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.TransactionType;
import net.dragberry.expman.query.TransactionTypeCreateQuery;
import net.dragberry.expman.repository.CustomerRepo;
import net.dragberry.expman.repository.TransactionTypeRepo;
import net.dragberry.expman.util.ResultFactory;
import net.dragberry.expman.util.Transformers;

@Service
@Transactional
public class TransactionTypeServiceBean implements TransactionTypeService {

	@Autowired
	private TransactionTypeRepo transactionTypeRepo;
	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public ResultTO<TransactionTypeTO> findTransactionTypeByKey(Long transactionKey) {
		TransactionType transaction = transactionTypeRepo.findOne(transactionKey);
		TransactionTypeTO transactionTypeTO = Transformers.getTransactionTypeTransformer().transform(transaction);
		return ResultFactory.createResult(transactionTypeTO);
	}

	@Override
	public ResultListTO<TransactionTypeTO> findTransactionTypeListForCustomer(Long customerKey) {
		Customer customer = customerRepo.findOne(customerKey);
		List<TransactionType> list = transactionTypeRepo.findByCustomer(customer);
		List<TransactionTypeTO> toList = new ArrayList<>();
		for (TransactionType transactionType : list) {
			TransactionTypeTO to = Transformers.getTransactionTypeTransformer().transform(transactionType);
			toList.add(to);
		}
		return ResultFactory.createResultList(toList);
	}

	@Override
	public ResultTO<TransactionTypeTO> createTransactionType(TransactionTypeCreateQuery query) {
		TransactionType tt = new TransactionType();
		tt.setName(query.getName());
		Long customerKey = query.getCustomerKey();
		Customer customer = customerRepo.findOne(customerKey);
		tt.setCustomer(customer);
		if (query.getParentKey() != null) {
			TransactionType parent = transactionTypeRepo.findOne(query.getParentKey());
			tt.setParentType(parent);
			tt.setLevel(parent.getLevel() + 1);
		} else {
			tt.setLevel(0);
		}
		tt = transactionTypeRepo.save(tt);
		TransactionTypeTO newTransactionTypeTO = Transformers.getTransactionTypeTransformer().transform(tt);
		return ResultFactory.createResult(newTransactionTypeTO);
	}

}
