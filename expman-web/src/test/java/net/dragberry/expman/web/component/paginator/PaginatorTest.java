package net.dragberry.expman.web.component.paginator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PaginatorTest {
	
	private Paginator paginator = new Paginator();

	@Test
	public void testGetPageNumberListSmall() {
		List<Integer> expected = Arrays.asList(1, 2);
		paginator.setCurrentPage(1);
		paginator.setTotalPages(2);
		paginator.setPageSize(2);
		assertThat(paginator.getPageNumberList(), is(expected));
	}
	
	@Test
	public void testGetPageNumberListFirstPage() {
		List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
		paginator.setCurrentPage(1);
		paginator.setTotalPages(20);
		paginator.setPageSize(20);
		assertThat(paginator.getPageNumberList(), is(expected));
	}
	
	@Test
	public void testGetPageNumberListMiddlePage() {
		List<Integer> expected = Arrays.asList(7,8,9,10,11);
		paginator.setCurrentPage(9);
		paginator.setTotalPages(20);
		paginator.setPageSize(20);
		assertThat(paginator.getPageNumberList(), is(expected));
	}
	
	@Test
	public void testGetPageNumberListLastPage() {
		List<Integer> expected = Arrays.asList(18,19,20,21,22);
		paginator.setCurrentPage(20);
		paginator.setTotalPages(20);
		paginator.setPageSize(20);
		assertThat(paginator.getPageNumberList(), is(expected));
	}
	
	@Test
	public void testIsFirstPageTrue() {
		paginator.setCurrentPage(1);
		paginator.setTotalPages(20);
		paginator.setPageSize(20);
		assertTrue("It is not first page", paginator.isFirstPage());
	}
	
	@Test
	public void testIsFirstPageFalse() {
		paginator.setCurrentPage(3);
		paginator.setTotalPages(20);
		paginator.setPageSize(20);
		assertFalse("It is first page", paginator.isFirstPage());
	}


	@Test
	public void testIsLastPageTrue() {
		paginator.setCurrentPage(20);
		paginator.setTotalPages(20);
		paginator.setPageSize(20);
		assertTrue("It is not last page", paginator.isLastPage());
	}
	
	@Test
	public void testIsLastPageFalse() {
		paginator.setCurrentPage(0);
		paginator.setTotalPages(20);
		paginator.setPageSize(20);
		assertFalse("It is last page", paginator.isLastPage());
	}

}
