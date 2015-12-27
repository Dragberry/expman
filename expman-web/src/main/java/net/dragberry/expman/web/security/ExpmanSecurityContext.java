package net.dragberry.expman.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ExpmanSecurityContext {

	private static final long ANONYMOUS_CUSTOMER_KEY = 2L;
	
	private static final String ANONYMOUS_CUSTOMER_NAME = "Guest";

	public static Long getCustomerKey() {
		CustomerDetails details = getCustomerDetails();
		return details == null ? ANONYMOUS_CUSTOMER_KEY : details.getCustomerKey();
	}

	public static String getCustomerName() {
		CustomerDetails details = getCustomerDetails();
		return details == null ? ANONYMOUS_CUSTOMER_NAME : details.getUsername();
	}
	
	private static Authentication getAuthentification() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	private static CustomerDetails getCustomerDetails() {
		Authentication authentication = getAuthentification();
		if (authentication != null) {
			Object principal =  getAuthentification().getPrincipal();
			if (principal instanceof CustomerDetails) {
				return (CustomerDetails) principal;
			}
		}
		return null;
	}
}
