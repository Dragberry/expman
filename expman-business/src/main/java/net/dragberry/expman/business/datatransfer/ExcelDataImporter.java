package net.dragberry.expman.business.datatransfer;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.dragberry.expman.domain.Account;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Instruction;
import net.dragberry.expman.domain.InstructionClassification;
import net.dragberry.expman.domain.InstructionStatus;
import net.dragberry.expman.domain.Transaction;
import net.dragberry.expman.domain.TransactionType;
import net.dragberry.expman.domain.Account.AccountType;
import net.dragberry.expman.domain.CounterParty;
import net.dragberry.expman.repository.AccountRepo;
import net.dragberry.expman.repository.CounterPartyRepo;
import net.dragberry.expman.repository.CustomerRepo;
import net.dragberry.expman.repository.InstructionRepo;
import net.dragberry.expman.repository.TransactionRepo;
import net.dragberry.expman.repository.TransactionTypeRepo;

@Service
public class ExcelDataImporter implements DataImporter {

	private static final long serialVersionUID = 4642094823416794248L;
	
	private static final Logger LOG = LogManager.getLogger(ExcelDataImporter.class.getName());
	
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private TransactionTypeRepo transactionTypeRepo;
	@Autowired
	private CounterPartyRepo counterPartyRepo;
	@Autowired
	private TransactionRepo transactionRepo;
	@Autowired
	private InstructionRepo instructionRepo;

	@Override
	public void doImport(InputStream is) throws Exception {
		try (Workbook wb = new HSSFWorkbook(is)) {
			processSheet(Account.class, wb, (row) -> {
				Account account = new Account();
				account.setNumber(row.getCell(1).getStringCellValue());
				account.setCurrency(row.getCell(2).getStringCellValue());
				account.setType(AccountType.valueOf(row.getCell(3).getStringCellValue()));
				String customerName = row.getCell(4).getStringCellValue();
				Customer customer = customerRepo.findByCustomerName(customerName);
				account.setCustomer(customer);
				return accountRepo.save(account);
			});
			
			processSheet(TransactionType.class, wb, (row) -> {
				TransactionType type = new TransactionType();
				type.setName(row.getCell(1).getStringCellValue());
				Cell parentTypeCell = row.getCell(5);
				Integer level = 0;
				if (parentTypeCell != null) {
					TransactionType parent = transactionTypeRepo.findByName(StringUtils.trimToEmpty(parentTypeCell.getStringCellValue()));
					if (parent != null) {
						level = parent.getLevel() + 1;
					}
					type.setParentType(parent);
				}
				type.setLevel(level);
				
				String customerName = row.getCell(2).getStringCellValue();
				Customer customer = customerRepo.findByCustomerName(customerName);
				type.setCustomer(customer);
				return transactionTypeRepo.save(type);
			});
			
			processSheet(CounterParty.class, wb, (row) -> {
				CounterParty cp = new CounterParty();
				cp.setName(row.getCell(1).getStringCellValue());
				cp.setPhysical(row.getCell(4).getBooleanCellValue());
				String customerName = row.getCell(2).getStringCellValue();
				Customer customer = customerRepo.findByCustomerName(customerName);
				cp.setCustomer(customer);
				return counterPartyRepo.save(cp);
			});
			
			Map<Long, Instruction> insMap = new HashMap<>();
			processSheet(Transaction.class, wb, (row) -> {
				Long insIndex = Long.valueOf((long) row.getCell(14).getNumericCellValue());
				Instruction ins = insMap.get(insIndex);
				if (ins == null) {
					ins = new Instruction();
					insMap.put(insIndex, ins);
				}
				
				CounterParty cp = counterPartyRepo.findByName(row.getCell(8).getStringCellValue());
				ins.setCounterParty(cp);
				
				Customer customer = customerRepo.findByCustomerName(row.getCell(12).getStringCellValue());
				ins.setCustomer(customer);
				
				ins.setStatus(InstructionStatus.PROCESSING);
				
				InstructionClassification classification = InstructionClassification.valueOf(row.getCell(15).getStringCellValue());
				ins.setClassification(classification);
				ins = instructionRepo.save(ins);
				
				Transaction tr = new Transaction();
				tr.setInstruction(ins);
				
				Account account = accountRepo.findByNumber(row.getCell(1).getStringCellValue());
				tr.setAccount(account);
				BigDecimal amount = new BigDecimal(row.getCell(3).getStringCellValue());
				tr.setAmount(amount);
				tr.setCurrency(row.getCell(4).getStringCellValue());
				tr.setProcessingDate(row.getCell(5).getDateCellValue());
				tr.setType(row.getCell(6).getStringCellValue());
				tr.setDescription(row.getCell(7).getStringCellValue());
				
				
				TransactionType tt = transactionTypeRepo.findByName(row.getCell(10).getStringCellValue());
				tr.setTransactionType(tt);
				
				
				return transactionRepo.save(tr);
			});
		}
	}
	
	private <T extends Serializable> void processSheet(Class<T> clazz, Workbook wb, EntityCreator<T> creator) {
		Sheet sheet = wb.getSheet(clazz.getSimpleName());
		Iterator<Row> rowIter = sheet.iterator();
		while (rowIter.hasNext()) {
			Row row = rowIter.next();
			try {
				createEntity(creator, row);
			} catch (Exception e) {
				LOG.error("Error while processing " + clazz.getSimpleName() + " record " +  row.getRowNum(), e);
			}
		}
	}
	
	@Transactional
	private <T extends Serializable> void createEntity(EntityCreator<T> creator, Row row) {
		creator.createEntity(row);
	}

	@FunctionalInterface
	private static interface EntityCreator<T extends Serializable> {
		
		T createEntity(Row row);
		
	}
}
