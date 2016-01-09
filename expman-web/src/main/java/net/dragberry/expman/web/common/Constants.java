package net.dragberry.expman.web.common;

public interface Constants {

	static public interface Path {
		// home
		String HOME = "/";
		String LOGIN = "/login";
		String LOGOUT = "/logout";
		String ACCESS_DENIED = "/access-denied";
		String REGISTRATION = "/registration";
		// transaction
		String TRANSACTION_CREATE = "/transaction/create";
		String TRANSACTION_LIST = "/transaction/list";
		String TRANSACTION_DELETE = "/transaction/delete/{transactionId}";
		// transaction-type
		String TRANSACTION_TYPE_CREATE = "/transaction-type/create";
		// counter-party
		String COUNTER_PARTY_CREATE = "/counter-party/create";
		// account
		String ACCOUNT_LIST = "/account/list";
		// admin
		String ADMIN = "/admin";
		String EXPORT = ADMIN + "/export";
		String DO_EXPORT = ADMIN + "/do-export";
		
	}
	
	static public interface View {
		// home
		String HOME = "home/index";
		String LOGIN = "home/login";
		String HOME_REDIRECT = "redirect:" + Path.HOME;
		String REGISTRATION = "home/registration";
		// errors
		String ACCESS_DENIED = "error/access-denied";
		String PAGE_NOT_FOUND = "error/page-not-found";
		String SERVER_ERROR = "error/server-error";
		// transaction
		String TRANSACTION_CREATE = "transaction/create-transaction";
		String TRANSACTION_LIST = "transaction/list-transaction";
		// transaction-type
		String TRANSACTION_TYPE_CREATE = "transaction-type/create-transaction-type";
		// counter-party
		String COUNTER_PARTY_CREATE = "counter-party/create-counter-party";
		// account
		String ACCOUNT_LIST = "account/list-account";
		// admin
		String EXPORT = "admin/export";
		
	}
}
