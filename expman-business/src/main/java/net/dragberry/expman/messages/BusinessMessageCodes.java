package net.dragberry.expman.messages;

public interface BusinessMessageCodes {
	
	String DOMAIN = "BusinessMessages";
	
	interface CreateCustomer {
		String EMAIL_IS_MANDATORY = "000001";
		String EMAIL_IS_NOT_UNIQUE = "000002";
		String EMAIL_IS_INVALID = "000003";
		
		String CUSTOMER_NAME_IS_MANDATORY = "000004";
		String CUSTOMER_NAME_IS_NOT_UNIQUE = "000005";
		
		String PASSWORD_DOES_NOT_MATCH = "000006";
		String PASSWORD_HAS_INVALID_LENGTH = "000007";
	}
	
	interface CreateTransaction {
		
		String DESCRIPTION_IS_MANDATORY = "000051";
		String DESCRIPTION_IS_TOO_LARGE = "000052";
		
		String AMOUNT_IS_MANDATORY = "000053";
		String AMOUNT_IS_INCORRECT = "000054";
		
		String CURRENCY_IS_NOT_MATCH_WITH_ACCOUNT_CURRENCY = "000055";
	}
}
