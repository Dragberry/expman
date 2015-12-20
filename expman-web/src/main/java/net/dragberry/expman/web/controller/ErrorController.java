package net.dragberry.expman.web.controller;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import net.dragberry.expman.web.common.Constants;

@ControllerAdvice
public class ErrorController implements Serializable {

	private static final long serialVersionUID = 7722243327308713439L;
	
	@RequestMapping(value = Constants.Path.ACCESS_DENIED)
	public String accessDenied() {
		return Constants.View.ACCESS_DENIED;
	}
	
	//TODO
	@ExceptionHandler(NoSuchRequestHandlingMethodException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String pageNotFound(NoSuchRequestHandlingMethodException e) {
		return Constants.View.PAGE_NOT_FOUND;
	}

}
