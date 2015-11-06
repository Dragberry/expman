package net.dragberry.expman.util;

import net.dragberry.expman.bean.IssueTO;

public final class IssueFactory {

	private IssueFactory() {
	}
	
	public static IssueTO createIssue(String issueCode) {
		return createIssue(issueCode, null);
	}
	
	public static IssueTO createIssue(String issueCode, String domain) {
		IssueTO issueTO = new IssueTO();
		issueTO.setIssueCode(issueCode);
		issueTO.setDomain(domain);
		return issueTO;
	}
}
