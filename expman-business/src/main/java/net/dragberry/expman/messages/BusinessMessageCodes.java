package net.dragberry.expman.messages;

public interface BusinessMessageCodes {
	
	String DOMAIN = "BusinessMessages";
	
	String CUSTOMER_EMAIL_IS_ABSENT = "000001";
	String CUSTOMER_EMAIL_IS_EXIST = "000002";
	String CUSTOMER_NAME_IS_ABSENT = "000003";
	String CUSTOMER_NAME_IS_EXIST = "000004";
	String CUSTOMER_PASSWORD_DO_NOT_MATCH = "000005";
}
