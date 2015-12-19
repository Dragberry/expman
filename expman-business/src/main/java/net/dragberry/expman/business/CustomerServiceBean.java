package net.dragberry.expman.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.bean.IssueTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Role;
import net.dragberry.expman.messages.BusinessMessageCodes;
import net.dragberry.expman.query.CustomerCreateQuery;
import net.dragberry.expman.repository.CustomerRepo;
import net.dragberry.expman.repository.RoleRepo;
import net.dragberry.expman.util.IssueFactory;
import net.dragberry.expman.util.ResultFactory;
import net.dragberry.expman.util.Transformers;

@Service
@Transactional
public class CustomerServiceBean implements CustomerService {
	
	private static final Logger LOG = LogManager.getLogger(CustomerServiceBean.class.getName());
	
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public ResultTO<CustomerTO> findByCustomerName(String customerName) {
		Customer customer = customerRepo.findByCustomerName(customerName);
		CustomerTO customerTO =  Transformers.getCustomerTransformer().transform(customer);
		return ResultFactory.createResult(customerTO);
	}

	@Override
	public ResultTO<CustomerTO> createCustomer(CustomerCreateQuery customerQuery) {
		List<IssueTO> issueLog = new ArrayList<>();
		if (StringUtils.isBlank(customerQuery.getEmail())) {
			issueLog.add(IssueFactory.createIssue(BusinessMessageCodes.CUSTOMER_EMAIL_IS_ABSENT, BusinessMessageCodes.DOMAIN));
			LOG.warn("Customer's e-mail is absent");
		}
		if (customerRepo.ifCustomerEmailExist(customerQuery.getEmail())) {
			issueLog.add(IssueFactory.createIssue(BusinessMessageCodes.CUSTOMER_EMAIL_IS_EXIST, BusinessMessageCodes.DOMAIN));
			LOG.warn("Customer's e-mail has already present");
		}
		if (StringUtils.isBlank(customerQuery.getCustomerName())) {
			issueLog.add(IssueFactory.createIssue(BusinessMessageCodes.CUSTOMER_NAME_IS_ABSENT, BusinessMessageCodes.DOMAIN));
			LOG.warn("Customer name is absent");
		}
		if (customerRepo.ifCustomerNameExist(customerQuery.getCustomerName())) {
			issueLog.add(IssueFactory.createIssue(BusinessMessageCodes.CUSTOMER_NAME_IS_EXIST, BusinessMessageCodes.DOMAIN));
			LOG.warn("Customer name has already present");
		}
		if (!StringUtils.equals(customerQuery.getPassword(), customerQuery.getPasswordRepeated())) {
			issueLog.add(IssueFactory.createIssue(BusinessMessageCodes.CUSTOMER_PASSWORD_DO_NOT_MATCH, BusinessMessageCodes.DOMAIN));
			LOG.warn("Password is not matching");
		}
		Customer customer = new Customer();
		customer.setCustomerKey(customerQuery.getCustomerKey());
		customer.setCustomerName(customerQuery.getCustomerName());
		customer.setBirthDate(customerQuery.getBirthdate());
		customer.setEmail(customerQuery.getEmail());
		customer.setEnabled(customerQuery.isEnabled());
		customer.setFirstName(customerQuery.getFirstName());
		customer.setLastName(customerQuery.getLastName());
		customer.setPassword(customerQuery.getPassword());
		Set<Role> roles = roleRepo.findByRoleNameIn(customerQuery.getRoles());
		customer.setRoles(roles);
		
		if (issueLog.isEmpty()) {
			customer = customerRepo.save(customer);
		}
		CustomerTO customerTO =  Transformers.getCustomerTransformer().transform(customer);
		return ResultFactory.createResult(customerTO, issueLog);
		
	}
	
	

}
