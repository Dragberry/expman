package net.dragberry.expman.web.component.paginator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Paginator implements Serializable {

	private static final int NUMBER_OF_DISPLAYED_PAGES = 5;

	private static final int FIRST_PAGE = 1;

	private static final long serialVersionUID = 7342985138924271180L;
	
	private int currentPage;
	
	private int pageSize;
	
	private int totalPages;
	
	public List<Integer> getPageNumberList() {
		int displayPageNumbers = totalPages <= NUMBER_OF_DISPLAYED_PAGES ? totalPages : NUMBER_OF_DISPLAYED_PAGES; 
		List<Integer> list = new ArrayList<>(displayPageNumbers);
		int firstNumber = currentPage - NUMBER_OF_DISPLAYED_PAGES/2 < 0 ? 1 : currentPage - NUMBER_OF_DISPLAYED_PAGES/2;
		for (int index = firstNumber; index < firstNumber + displayPageNumbers; index++) {
			list.add(index);
		}
		return list;
	}

	public boolean isFirstPage() {
		return currentPage == FIRST_PAGE;
	}
	
	public boolean isLastPage() {
		return currentPage == totalPages;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if (this.currentPage == 0 && currentPage != 0) {
			this.currentPage = currentPage;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

}
