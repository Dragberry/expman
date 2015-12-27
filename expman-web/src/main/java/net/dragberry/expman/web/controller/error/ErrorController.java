package net.dragberry.expman.web.controller.error;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import net.dragberry.expman.web.common.Constants;

@Controller
public class ErrorController implements Serializable {

	private static final long serialVersionUID = 7722243327308713439L;
	
	@RequestMapping(value = Constants.Path.ACCESS_DENIED)
	public String accessDenied() {
		return Constants.View.ACCESS_DENIED;
	}
	
}
