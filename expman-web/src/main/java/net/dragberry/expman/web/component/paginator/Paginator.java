package net.dragberry.expman.web.component.paginator;

import java.util.List;

public interface Paginator {

	int NUMBER_OF_DISPLAYED_PAGES = 5;

	int FIRST_PAGE = 1;
	
	List<Integer> getPageNumberList();
	
	boolean isLastPage();
	
	boolean isFirstPage();
	
	int getCurrentPage();
	
	void setCurrentPage(int currentPage);
	
	int getPageSize();
	
	void setPageSize(int pageSize);
	
	public int getTotalPages();
	
	void setTotalPages(int totalPages);
}
