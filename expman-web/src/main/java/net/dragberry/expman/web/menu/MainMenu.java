package net.dragberry.expman.web.menu;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("session")
public class MainMenu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String test = "test";

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	
}
