package net.dragberry.expman.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import net.dragberry.expman.bean.InstructionTO;
import net.dragberry.expman.bean.IssueTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.domain.Account;
import net.dragberry.expman.domain.CounterParty;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Instruction;
import net.dragberry.expman.domain.InstructionClassification;
import net.dragberry.expman.domain.InstructionStatus;
import net.dragberry.expman.domain.Transaction;
import net.dragberry.expman.domain.TransactionType;
import net.dragberry.expman.messages.BusinessMessageCodes;
import net.dragberry.expman.query.InstructionCreateQuery;
import net.dragberry.expman.query.TransactionCreateQuery;
import net.dragberry.expman.repository.AccountRepo;
import net.dragberry.expman.repository.CounterPartyRepo;
import net.dragberry.expman.repository.CustomerRepo;
import net.dragberry.expman.repository.InstructionRepo;
import net.dragberry.expman.repository.TransactionRepo;
import net.dragberry.expman.repository.TransactionTypeRepo;
import net.dragberry.expman.util.IssueFactory;
import net.dragberry.expman.util.ResultFactory;
import net.dragberry.expman.util.Transformers;

public class InstructionServiceBean implements InstructionService {

	private static final long serialVersionUID = -359203895555274904L;
	
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private InstructionRepo instructionRepo;
	@Autowired
	private TransactionRepo transactionRepo;
	@Autowired
	private TransactionTypeRepo transactionTypeRepo;
	@Autowired
	private CounterPartyRepo counterPartyRepo;
	@Autowired
	private AccountRepo accountRepo;
	
	@Override
	public ResultTO<InstructionTO> createInstruction(InstructionCreateQuery query) {
		Instruction ins = new Instruction();
		List<IssueTO> issues = new ArrayList<>();
		ins.setClassification(InstructionClassification.valueOf(query.getClassification()));
		
		Customer customer = customerRepo.findOne(query.getCustomerKey());
		ins.setCustomer(customer);
		
		CounterParty cp = counterPartyRepo.findOne(query.getCounterPartyKey());
		ins.setCounterParty(cp);
		
		ins = instructionRepo.save(ins);
		
		List<Transaction> trList = new ArrayList<>();
		for (TransactionCreateQuery trQuery : query.getTransactionList()) {
			trQuery.setInstructionKey(ins.getInstructionKey());
			TransactionResult trResult = createTransaction(trQuery);
			if (trResult.getIssueLog().isEmpty()) {
				trList.add(trResult.getTransaction());
			} else {
				issues.addAll(trResult.getIssueLog());
			}
		}
		ins.setTransactions(trList);
		if (issues.isEmpty()) {
			ins.setStatus(InstructionStatus.SUCCESS);
			ins = instructionRepo.save(ins);
		}
		
		InstructionTO insTO = new InstructionTO();
		insTO.setInstructionKey(ins.getInstructionKey());
		insTO.setClassification(ins.getClassification().name());
		for (Transaction transaction : trList) {
			insTO.addTransaction(Transformers.getTransactionTransformer().transform(transaction));
		}
		
		return ResultFactory.createResult(insTO, issues);
	}
	
	private TransactionResult createTransaction(TransactionCreateQuery query) {
		List<IssueTO> issues = new ArrayList<>();
		Transaction tr = new Transaction();
		tr.setTransactionKey(query.getTransactionKey());
		if (query.getAmount() == null) {
			issues.add(IssueFactory.createIssue(BusinessMessageCodes.CreateTransaction.AMOUNT_IS_MANDATORY));
		} else if (query.getAmount().signum() == -1) {
			issues.add(IssueFactory.createIssue(BusinessMessageCodes.CreateTransaction.AMOUNT_IS_INCORRECT));
		}
		tr.setAmount(query.getAmount());
		
		if (StringUtils.isBlank(query.getDescription())) {
			issues.add(IssueFactory.createIssue(BusinessMessageCodes.CreateTransaction.DESCRIPTION_IS_MANDATORY));
		} else if (query.getDescription().length() > 255) {
			issues.add(IssueFactory.createIssue(BusinessMessageCodes.CreateTransaction.DESCRIPTION_IS_TOO_LARGE));
		}
		tr.setDescription(query.getDescription());
		
		tr.setProcessingDate(query.getProcessingDate());
		
		TransactionType type = transactionTypeRepo.findOne(query.getTransactionTypeKey());
		tr.setTransactionType(type);
		
		Account account = accountRepo.findOne(query.getAccountKey());
		tr.setAccount(account);
		
		if (!account.getCurrency().equals(query.getCurrency())) {
			issues.add(IssueFactory.createIssue(BusinessMessageCodes.CreateTransaction.CURRENCY_IS_NOT_MATCH_WITH_ACCOUNT_CURRENCY));
		}
		tr.setCurrency(query.getCurrency());
		
		tr.setType(query.getType());
		
		if (issues.isEmpty()) {
			tr = transactionRepo.save(tr);
		}
		return new TransactionResult(tr, issues);
	}
	
	private static class TransactionResult {
		
		private Transaction transaction;
		private List<IssueTO> issueLog;
		
		public TransactionResult(Transaction tr, List<IssueTO> issueLog) {
			this.transaction = tr;
			this.issueLog = issueLog;
		}
		
		public Transaction getTransaction() {
			return transaction;
		}

		public List<IssueTO> getIssueLog() {
			return issueLog;
		}
		
	}

}
