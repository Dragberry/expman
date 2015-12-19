package net.dragberry.expman.web.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.dragberry.expman.web.common.Constants;
import net.dragberry.expman.web.security.ExpmanSecurityContext;

@Controller
public class HomeController implements Serializable {

	private static final long serialVersionUID = -1844510470973975486L;
	
	@RequestMapping(value = Constants.Path.HOME)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(Constants.View.HOME);
		modelAndView.addObject("customerName", ExpmanSecurityContext.getCustomerName());
		return modelAndView;
	}

}
