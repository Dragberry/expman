package net.dragberry.expman.web.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;

import net.dragberry.expman.bean.AccountBalanceTO;

@Controller
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountSession implements Serializable {

	private static final long serialVersionUID = 745530773530408180L;
	
	private boolean initialized;
	
	private List<AccountBalanceTO> accountBalanceList = new ArrayList<>();

	public List<AccountBalanceTO> getAccountBalanceList() {
		return accountBalanceList;
	}

	public void setAccountBalanceList(List<AccountBalanceTO> accountBalanceList) {
		this.accountBalanceList = accountBalanceList;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
	
}
