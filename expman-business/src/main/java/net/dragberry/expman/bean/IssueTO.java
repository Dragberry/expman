package net.dragberry.expman.bean;

public class IssueTO implements TransferObject {

	private static final long serialVersionUID = -2649842939376186054L;
	
	private String issueCode;
	
	private String domain;
	
	private String[] params;

	public String getIssueCode() {
		return issueCode;
	}

	public void setIssueCode(String issueCode) {
		this.issueCode = issueCode;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}
	
}
