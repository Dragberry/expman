package net.dragberry.expman.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ExpmanSecurityContext {

	private static final long ANONYMOUS_CUSTOMER_KEY = 2L;

	public static Long getCustomerKey() {
		Object principal =  getAuthentification().getPrincipal();
		if (principal instanceof CustomerDetails) {
			return ((CustomerDetails) principal).getCustomerKey();
		} else {
			return ANONYMOUS_CUSTOMER_KEY;
		}
	}

	public static String getCustomerName() {
		return getAuthentification().getName();
	}
	
	private static Authentication getAuthentification() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
