package net.dragberry.expman.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.dragberry.expman.menu.config.MenuConfig;

@Configuration
@Import(value = { BusinessConfig.class, MenuConfig.class, SecurityConfig.class })
public class AppConfig {
	
	@Bean
	public CommonAnnotationBeanPostProcessor postProcessor() {
		return new CommonAnnotationBeanPostProcessor();
	}

}
