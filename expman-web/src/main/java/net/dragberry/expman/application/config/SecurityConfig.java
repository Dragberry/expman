package net.dragberry.expman.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import net.dragberry.expman.business.CustomerService;
import net.dragberry.expman.web.common.Constants;
import net.dragberry.expman.web.security.CustomerSecurityService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String SHA_256_ALGORITHM = "SHA-256";
	@Autowired
	private CustomerService customerService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);
		http
		.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/customer").hasRole("CUSTOMER")
			.antMatchers("/create-interchange").hasRole("CUSTOMER")
			.antMatchers("/registration").not().authenticated()
			.anyRequest().permitAll()
		.and()
			.formLogin()
		.and()
			.exceptionHandling().accessDeniedPage(Constants.Path.ACCESS_DENIED)
		.and()
			.httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new CustomerSecurityService(customerService)).passwordEncoder(new StandardPasswordEncoder(SHA_256_ALGORITHM));
	}
}
