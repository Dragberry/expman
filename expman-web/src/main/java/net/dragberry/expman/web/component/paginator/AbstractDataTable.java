package net.dragberry.expman.web.component.paginator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AbstractDataTable<T extends Serializable> implements DataTable<T> {
	
	private String id;
	
	private List<T> data = new ArrayList<>();
	
	private Paginator paginator = new PaginatorImpl();

	@Override
	public List<T> getData() {
		return data;
	}

	@Override
	public Paginator getPaginator() {
		return paginator;
	}

	@Override
	public String getId() {
		return id;
	}

}
