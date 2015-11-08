package net.dragberry.expman.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public abstract class AbstractResultTO<T extends TransferObject> implements TransferObject {

	private static final long serialVersionUID = -6404191892950150777L;
	
	private List<IssueTO> issueLog = new ArrayList<>();
	
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
