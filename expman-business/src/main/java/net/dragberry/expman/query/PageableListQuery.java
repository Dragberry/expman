package net.dragberry.expman.query;

import net.dragberry.expman.bean.TransferObject;

public abstract class PageableListQuery implements TransferObject {

	private static final long serialVersionUID = -695515671313179039L;
	
	private int pageNumber = 1;
	
	private int pageSize = 20;

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
