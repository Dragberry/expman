package net.dragberry.expman.util;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import net.dragberry.expman.bean.AbstractResultTO;
import net.dragberry.expman.bean.IssueTO;
import net.dragberry.expman.bean.ResultListTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.bean.TransferObject;

public final class ResultFactory {

	private ResultFactory() {
	}
	
	public static <T extends TransferObject> ResultTO<T> createResult(T resultObject) {
		return createResult(resultObject, null);
	}
	
	public static <T extends TransferObject> ResultTO<T> createResult(T resultObject, Collection<IssueTO> issueLog) {
		ResultTO<T> result = new ResultTO<>(resultObject);
		addIssues(result, issueLog);
		return result;
	}
	
	public static <T extends TransferObject> ResultListTO<T> createResultList(List<T> resultList) {
		return createResultList(resultList, null);
	}
	
	public static <T extends TransferObject> ResultListTO<T> createResultList(List<T> resultList, Collection<IssueTO> issueLog) {
		ResultListTO<T> result = new ResultListTO<>();
		addIssues(result, issueLog);
		return result;
	}
	
	private static <T extends TransferObject> void addIssues(AbstractResultTO<T> resultTO, Collection<IssueTO> issueLog) {
		if (CollectionUtils.isNotEmpty(issueLog)) {
			for (IssueTO issue : issueLog) {
				resultTO.addIssue(issue);
			}
		}
	}
}
