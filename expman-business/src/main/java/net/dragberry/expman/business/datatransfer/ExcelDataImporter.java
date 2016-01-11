package net.dragberry.expman.business.datatransfer;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.dragberry.expman.domain.Account;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Transaction;
import net.dragberry.expman.domain.TransactionType;
import net.dragberry.expman.domain.Account.AccountType;
import net.dragberry.expman.domain.CounterParty;
import net.dragberry.expman.repository.AccountRepo;
import net.dragberry.expman.repository.CounterPartyRepo;
import net.dragberry.expman.repository.CustomerRepo;
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
				String customerName = row.getCell(2).getStringCellValue();
				Customer customer = customerRepo.findByCustomerName(customerName);
				type.setCustomer(customer);
				return transactionTypeRepo.save(type);
			});
			
			processSheet(CounterParty.class, wb, (row) -> {
				CounterParty cp = new CounterParty();
				cp.setName(row.getCell(1).getStringCellValue());
				String customerName = row.getCell(2).getStringCellValue();
				Customer customer = customerRepo.findByCustomerName(customerName);
				cp.setCustomer(customer);
				return counterPartyRepo.save(cp);
			});
			
			processSheet(Transaction.class, wb, (row) -> {
				Transaction tr = new Transaction();
				Account account = accountRepo.findByNumber(row.getCell(1).getStringCellValue());
				tr.setAccount(account);
				BigDecimal amount = new BigDecimal(row.getCell(3).getStringCellValue());
				tr.setAmount(amount);
				tr.setCurrency(row.getCell(4).getStringCellValue());
				tr.setProcessingDate(row.getCell(5).getDateCellValue());
				tr.setType(row.getCell(6).getStringCellValue());
				tr.setDescription(row.getCell(7).getStringCellValue());
				
				CounterParty cp = counterPartyRepo.findByName(row.getCell(8).getStringCellValue());
				tr.setCounterParty(cp);
				
				TransactionType tt = transactionTypeRepo.findByName(row.getCell(10).getStringCellValue());
				tr.setTransactionType(tt);
				
				Customer customer = customerRepo.findByCustomerName(row.getCell(12).getStringCellValue());
				tr.setCustomer(customer);
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
