package net.dragberry.expman.business.datatransfer;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.dragberry.expman.domain.Account;
import net.dragberry.expman.domain.CounterParty;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Transaction;
import net.dragberry.expman.domain.TransactionType;
import net.dragberry.expman.repository.AccountRepo;
import net.dragberry.expman.repository.CounterPartyRepo;
import net.dragberry.expman.repository.CustomerRepo;
import net.dragberry.expman.repository.TransactionRepo;
import net.dragberry.expman.repository.TransactionTypeRepo;

@Service
public class ExcelDataExporter implements DataExporter {

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
	@Transactional
	public byte[] doExport() throws Exception {
		try (Workbook wb = new HSSFWorkbook()) {
			createSheet(Customer.class, customerRepo, wb, (entity, row) -> {
				Cell cell = row.createCell(0);
				cell.setCellValue(entity.getCustomerKey());
				cell = row.createCell(1);
				cell.setCellValue(entity.getCustomerName());
				cell = row.createCell(2);
				cell.setCellValue(entity.getFirstName());
				cell = row.createCell(3);
				cell.setCellValue(entity.getLastName());
				cell = row.createCell(4);
				cell.setCellValue(entity.getPassword());
				cell = row.createCell(5);
				cell.setCellValue(entity.getEmail());
				cell = row.createCell(6);
				cell.setCellValue(entity.getEnabled());
				cell = row.createCell(7);
				cell.setCellValue(entity.getEmail());
				return row;
			});

			createSheet(Account.class, accountRepo, wb, (entity, row) -> {
				Cell cell = row.createCell(0);
				cell.setCellValue(entity.getAccountKey());
				cell = row.createCell(1);
				cell.setCellValue(entity.getNumber());
				cell = row.createCell(2);
				cell.setCellValue(entity.getCurrency());
				cell = row.createCell(3);
				cell.setCellValue(entity.getType().toString());
				cell = row.createCell(4);
				cell.setCellValue(entity.getCustomer().getCustomerName());
				cell = row.createCell(5);
				cell.setCellValue(entity.getCustomer().getCustomerKey());
				return row;
			});

			createSheet(TransactionType.class, transactionTypeRepo, wb, (entity, row) -> {
				Cell cell = row.createCell(0);
				cell.setCellValue(entity.getTransactionTypeKey());
				cell = row.createCell(1);
				cell.setCellValue(entity.getName());
				cell = row.createCell(2);
				cell.setCellValue(entity.getCustomer().getCustomerName());
				cell = row.createCell(3);
				cell.setCellValue(entity.getCustomer().getCustomerKey());
				if (entity.getParentType() != null) {
					cell = row.createCell(4);
					cell.setCellValue(entity.getParentType().getName());
					cell = row.createCell(5);
					cell.setCellValue(entity.getParentType().getTransactionTypeKey());
				}
				return row;
			});

			createSheet(CounterParty.class, counterPartyRepo, wb, (entity, row) -> {
				Cell cell = row.createCell(0);
				cell.setCellValue(entity.getCounterPartyKey());
				cell = row.createCell(1);
				cell.setCellValue(entity.getName());
				cell = row.createCell(2);
				cell.setCellValue(entity.getCustomer().getCustomerName());
				cell = row.createCell(3);
				cell.setCellValue(entity.getCustomer().getCustomerKey());
				return row;
			});

			createSheet(Transaction.class, transactionRepo, wb, (entity, row) -> {
				Cell cell = row.createCell(0);
				cell.setCellValue(entity.getTransactionKey());
				cell = row.createCell(1);
				cell.setCellValue(entity.getAccount().getNumber());
				cell = row.createCell(2);
				cell.setCellValue(entity.getAccount().getAccountKey());
				cell = row.createCell(3);
				cell.setCellValue(entity.getAmount().toString());
				cell = row.createCell(4);
				cell.setCellValue(entity.getCurrency());
				cell = row.createCell(5);
				cell.setCellValue(entity.getProcessingDate());
				
				CellStyle dateCellStyle = wb.createCellStyle();
				short df = wb.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss");
				dateCellStyle.setDataFormat(df);
				
				cell.setCellStyle(dateCellStyle);
				cell = row.createCell(6);
				cell.setCellValue(entity.getType());
				cell = row.createCell(7);
				cell.setCellValue(entity.getDescription());
				cell = row.createCell(8);
				cell.setCellValue(entity.getCounterParty().getName());
				cell = row.createCell(9);
				cell.setCellValue(entity.getCounterParty().getCounterPartyKey());
				cell = row.createCell(10);
				cell.setCellValue(entity.getTransactionType().getName());
				cell = row.createCell(11);
				cell.setCellValue(entity.getTransactionType().getTransactionTypeKey());
				cell = row.createCell(12);
				cell.setCellValue(entity.getCustomer().getCustomerName());
				cell = row.createCell(13);
				cell.setCellValue(entity.getCustomer().getCustomerKey());

				return row;
			});

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			baos.close();
			return baos.toByteArray();
		}
	}

	public <T extends Serializable> Sheet createSheet(Class<T> clazz, JpaRepository<T, Long> repo, Workbook wb,
			RowCreator<T> rowCreator) {
		Sheet sheet = wb.createSheet(clazz.getSimpleName());
		List<T> list = repo.findAll();
		int rowNumber = 0;
		for (T entity : list) {
			Row row = sheet.createRow(rowNumber++);
			rowCreator.createRow(entity, row);
		}
		return sheet;
	}

	@FunctionalInterface
	static interface RowCreator<T extends Serializable> {

		Row createRow(T entity, Row row);

	}

}
