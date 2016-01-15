package net.dragberry.expman.util;

import net.dragberry.expman.bean.IssueTO;

public final class IssueFactory {

	private IssueFactory() {
	}
	
	public static IssueTO createIssue(String issueCode, String... params) {
		return createIssue(issueCode, null, params);
	}
	
	public static IssueTO createIssue(String issueCode, String domain, String[] params) {
		IssueTO issueTO = new IssueTO();
		issueTO.setIssueCode(issueCode);
		issueTO.setDomain(domain);
		issueTO.setParams(params);
		return issueTO;
	}
}
