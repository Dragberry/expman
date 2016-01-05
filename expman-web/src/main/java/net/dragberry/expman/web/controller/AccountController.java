package net.dragberry.expman.web.controller;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.dragberry.expman.web.common.Constants;

@Controller
public class AccountController implements Serializable {

	private static final long serialVersionUID = 8887853545591915247L;
	
	@RequestMapping(value = Constants.Path.ACCOUNT_LIST)
	public ModelAndView accountList() {
		ModelAndView modelAndView = new ModelAndView(Constants.View.ACCOUNT_LIST);
		
		return modelAndView;
	}

}
