package net.dragberry.expman.application.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import net.dragberry.expman.application.config.AppConfig;
import net.dragberry.expman.application.config.WebConfig;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	private static final String THROW_EXCEPTION_IF_NO_HANDLER_FOUND = "throwExceptionIfNoHandlerFound";
	
	private static final Logger LOG = LogManager.getLogger(ApplicationInitializer.class.getName());
	
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setInitParameter(THROW_EXCEPTION_IF_NO_HANDLER_FOUND, Boolean.TRUE.toString());
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.addListener(new HttpSessionListener() {
			
			@Override
			public void sessionDestroyed(HttpSessionEvent se) {
				LOG.info("Session destroyed...");
			}
			
			@Override
			public void sessionCreated(HttpSessionEvent se) {
				se.getSession().setMaxInactiveInterval(30*60);
				LOG.info("Session started...");
			}
		});
	}
	
	
	
}
