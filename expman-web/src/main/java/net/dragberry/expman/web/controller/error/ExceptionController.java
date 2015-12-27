package net.dragberry.expman.web.controller.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import net.dragberry.expman.web.common.Constants;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String pageNotFound(Exception ex) {
		return Constants.View.PAGE_NOT_FOUND;
	}
	
	@ExceptionHandler(Exception.class)
	public String serverError(Exception ex) {
		return Constants.View.SERVER_ERROR;
	}

}
