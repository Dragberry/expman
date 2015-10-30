package net.dragberry.expman.application.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import net.dragberry.expman.application.config.DataConfig;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { DataConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { null };
	}
	
	
	
}
