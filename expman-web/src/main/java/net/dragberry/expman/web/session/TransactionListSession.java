package net.dragberry.expman.web.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;

import net.dragberry.expman.bean.TransactionTO;
import net.dragberry.expman.web.component.paginator.Paginator;
import net.dragberry.expman.web.component.paginator.PaginatorImpl;

@Controller
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TransactionListSession implements Serializable {

	private static final long serialVersionUID = -5402582380065026815L;
	
	private List<TransactionTO> transactionList = new ArrayList<>();
	
	private Paginator paginator;
	
	private boolean initialized;
	
	public List<TransactionTO> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<TransactionTO> transactionList) {
		this.transactionList = transactionList;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	public Paginator getPaginator() {
		if (paginator == null) {
			paginator = new PaginatorImpl();
		}
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

}
