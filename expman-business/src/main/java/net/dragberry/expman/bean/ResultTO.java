package net.dragberry.expman.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class ResultTO<T extends TransferObject> implements TransferObject {

	private static final long serialVersionUID = -5024523531166601727L;

	private T object;
	
	private List<IssueTO> issueLog = new ArrayList<>();
	
	public ResultTO(T resultObject) {
		this.object = resultObject;
	}
	
	public T getObject() {
		return object;
	}
	
	public List<IssueTO> getIssueLog() {
		return Collections.unmodifiableList(issueLog);
	}
	
	public void addIssue(IssueTO issue) {
		issueLog.add(issue);
	}
	
	public boolean hasIssues() {
		return CollectionUtils.isNotEmpty(issueLog);
	}
}
