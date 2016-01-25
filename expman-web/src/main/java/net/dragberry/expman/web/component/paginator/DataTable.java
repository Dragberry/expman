package net.dragberry.expman.web.component.paginator;

import java.io.Serializable;
import java.util.List;

public interface DataTable<T extends Serializable> {
	
	String getId();

	List<T> getData();
	
	Paginator getPaginator();
}
