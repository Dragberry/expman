package net.dragberry.expman.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import net.dragberry.expman.web.menu.MainMenu;

@Component
public class SuccessAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private MainMenu mainMenu;
	
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		super.handle(request, response, authentication);
		mainMenu.setTest(authentication.getName());
		
	}

	
}
