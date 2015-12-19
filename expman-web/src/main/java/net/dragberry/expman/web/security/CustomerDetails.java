package net.dragberry.expman.web.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomerDetails extends User {

	private static final long serialVersionUID = -2611246446268300417L;
	
	private Long customerKey;

	public CustomerDetails(Long customerKey, String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.customerKey = customerKey;
	}
	
	public CustomerDetails(Long customerKey, String customerName, String password, List<GrantedAuthority> authorities) {
		this(customerKey, customerName, password, true, true, true, true, authorities);
	}

	public Long getCustomerKey() {
		return customerKey;
	}

}
