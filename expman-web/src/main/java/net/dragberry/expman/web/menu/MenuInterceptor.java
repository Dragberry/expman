package net.dragberry.expman.web.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.dragberry.expman.web.security.ExpmanSecurityContext;

public class MenuInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private MainMenuBean mainMenuBean;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		mainMenuBean.initialize(ExpmanSecurityContext.getCustomerKey());
		modelAndView.addObject("mainMenu", mainMenuBean);
	}

}
