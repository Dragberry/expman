package net.dragberry.expman.web.menu;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;

import net.dragberry.expman.web.security.ExpmanSecurityContext;

@Controller
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MainMenuBean implements Serializable {

	private static final long serialVersionUID = 5082686956970424579L;
	
	private Long customerKey;
	
	private String test = "test";
	
	public void initialize(Long cutomerKey) {
		if (this.customerKey == null || !this.customerKey.equals(cutomerKey)) {
			this.customerKey = cutomerKey;
			test = ExpmanSecurityContext.getCustomerName();
		}
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Long getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(Long customerKey) {
		this.customerKey = customerKey;
	}
	
}
