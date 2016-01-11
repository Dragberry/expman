package net.dragberry.expman.query;

import java.util.Date;

public class TransactionListQuery extends PageableListQuery {

	private static final long serialVersionUID = -1810520919542080385L;

	private Long customerKey;
	
	private Date fromDate;
	
	private Date toDate;

	public Long getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(Long customerKey) {
		this.customerKey = customerKey;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
}
