package net.dragberry.expman.application;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.dragberry.expman.application.config.DataConfig;
import net.dragberry.expman.domain.Customer;
import net.dragberry.expman.domain.Role;
import net.dragberry.expman.repository.CustomerRepo;

public class Launcher {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(DataConfig.class);
		CustomerRepo customerRepo = context.getBean(CustomerRepo.class);
		Customer customer = new Customer();
		customer.setCustomerName("Maksim");
		customer.setPassword("password");
		customer.setEnabled(true);
		customerRepo.save(customer);
		
		
		Customer newCust = customerRepo.findOne(1L);
		System.out.println(newCust.getCustomerName());
		System.out.println(newCust.getPassword());
		System.out.println(newCust.getEnabled());
		for (Role role : newCust.getRoles()) {
			System.out.println(role.getRoleName());
		}
		
		
		context.close();
	}
}
