package net.dragberry.expman.web.security;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.dragberry.expman.bean.CustomerTO;
import net.dragberry.expman.business.CustomerService;

public class CustomerSecurityService implements UserDetailsService {
	
	private static final String ROLE_PREFIX = "ROLE_";
	
	private final CustomerService customerSevice;
	
	@Autowired
	public CustomerSecurityService(CustomerService customerSevice) {
		this.customerSevice = customerSevice;
	}

	@Override
	public UserDetails loadUserByUsername(String customerName) throws UsernameNotFoundException {
		CustomerTO customerTO = customerSevice.findByCustomerName(customerName).getObject();
		if (customerTO != null) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			for (String role : customerTO.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(createSpringRole(role)));
			}
			return new User(customerTO.getCustomerName(), customerTO.getPassword(), authorities);
		}
		throw new UsernameNotFoundException(MessageFormat.format("The customer '%s' is not found", customerName));
	}

	private String createSpringRole(String role) {
		return ROLE_PREFIX + role;
	}

}
