package net.dragberry.expman.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import net.dragberry.expman.web.controller.Controllers;

@Configuration
@EnableWebMvc
@Import(value = { SecurityConfig.class })
@ComponentScan(basePackageClasses = { Controllers.class })
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public UrlBasedViewResolver viewResolver() {
		UrlBasedViewResolver bean = new UrlBasedViewResolver();
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		bean.setViewClass(JstlView.class);
		return bean;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
