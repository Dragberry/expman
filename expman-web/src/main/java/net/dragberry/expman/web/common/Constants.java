package net.dragberry.expman.web.common;

public interface Constants {

	static public interface Path {
		// home
		String HOME = "/";
		String LOGOUT = "/logout";
		String REGISTRATION = "/registration";
		// interchange
		String INTERCHANGE_CREATE = "/interchange/create";
		// interchange-type
		String INTERCHANGE_TYPE_CREATE = "/interchange-type/create";
		// counter-party
		String COUNTER_PARTY_CREATE = "/counter-party/create";
	}
	
	static public interface View {
		// home
		String HOME = "home/index";
		String HOME_REDIRECT = "redirect:" + Path.HOME;
		String REGISTRATION = "home/registration";
		// interchange
		String INTERCHANGE_CREATE = "interchange/create-interchange";
		// interchange-type
		String INTERCHANGE_TYPE_CREATE = "interchange-type/create-interchange-type";
		// counter-party
		String COUNTER_PARTY_CREATE = "counter-party/create-counter-party";
		
	}
}
