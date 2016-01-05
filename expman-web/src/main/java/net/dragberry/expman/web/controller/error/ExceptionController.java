package net.dragberry.expman.web.controller.error;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import net.dragberry.expman.web.common.Constants;

@ControllerAdvice
public class ExceptionController {
	
	private static final Logger LOG = LogManager.getLogger(ExceptionController.class.getName());
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String pageNotFound(NoHandlerFoundException ex) {
		LOG.error("The handler has been not found!" , ex);
		return Constants.View.PAGE_NOT_FOUND;
	}
	
	@ExceptionHandler(Exception.class)
	public String serverError(Exception ex) {
		LOG.error("An exception has been occured!" , ex);
		return Constants.View.SERVER_ERROR;
	}

}
