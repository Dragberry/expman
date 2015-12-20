package net.dragberry.expman.web.common;

public interface Constants {

	static public interface Path {
		// home
		String HOME = "/";
		String LOGIN = "/login";
		String LOGOUT = "/logout";
		String ACCESS_DENIED = "/access-denied";
		String REGISTRATION = "/registration";
		// interchange
		String INTERCHANGE_CREATE = "/interchange/create";
		String INTERCHANGE_LIST = "/interchange/list";
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
		// errors
		String ACCESS_DENIED = "error/access-denied";
		String PAGE_NOT_FOUND = "error/page-not-found";
		// interchange
		String INTERCHANGE_CREATE = "interchange/create-interchange";
		String INTERCHANGE_LIST = "interchange/list-interchange";
		// interchange-type
		String INTERCHANGE_TYPE_CREATE = "interchange-type/create-interchange-type";
		// counter-party
		String COUNTER_PARTY_CREATE = "counter-party/create-counter-party";
		
	}
}
