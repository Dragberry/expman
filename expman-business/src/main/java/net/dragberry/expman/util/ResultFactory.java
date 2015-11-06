package net.dragberry.expman.util;

import java.util.Collection;
import org.apache.commons.collections4.CollectionUtils;

import net.dragberry.expman.bean.IssueTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.bean.TransferObject;

public final class ResultFactory {

	private ResultFactory() {
	}
	
	public <T extends TransferObject> ResultTO<T> createResult(T resultObject) {
		return createResult(resultObject, null);
	}
	
	public <T extends TransferObject> ResultTO<T> createResult(T resultObject, Collection<IssueTO> issueLog) {
		ResultTO<T> result = new ResultTO<>(resultObject);
		if (CollectionUtils.isNotEmpty(issueLog)) {
			for (IssueTO issue : issueLog) {
				result.addIssue(issue);
			}
		}
		return result;
	}
}
