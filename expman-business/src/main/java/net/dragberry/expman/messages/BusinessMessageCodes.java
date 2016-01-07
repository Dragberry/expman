package net.dragberry.expman.messages;

public interface BusinessMessageCodes {
	
	String DOMAIN = "BusinessMessages";
	
	String CUSTOMER_EMAIL_IS_ABSENT = "000001";
	String CUSTOMER_EMAIL_IS_EXIST = "000002";
	String CUSTOMER_NAME_IS_ABSENT = "000003";
	String CUSTOMER_NAME_IS_EXIST = "000004";
	String CUSTOMER_PASSWORD_DO_NOT_MATCH = "000005";
	
	static interface CreateTransaction {
		
		String DESCRIPTION_IS_MANDATORY = "000051";
		String DESCRIPTION_IS_TOO_LARGE = "000052";
		
		String AMOUNT_IS_MANDATORY = "000053";
		String AMOUNT_IS_INCORRECT = "000054";
		
		String CURRENCY_IS_NOT_MATCH_WITH_ACCOUNT_CURRENCY = "000055";
	}
}
