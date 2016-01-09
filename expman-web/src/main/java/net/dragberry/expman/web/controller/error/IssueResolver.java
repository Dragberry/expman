package net.dragberry.expman.web.controller.error;

import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;

import net.dragberry.expman.bean.IssueTO;

public class IssueResolver {
	
	private IssueResolver() {
	}
	
	public static Map<String, Object> resolve(List<IssueTO> issuesLog, Map<String, String> errorCodes, BindingResult bindingResult) {
		for (IssueTO issue : issuesLog) {
			String field = errorCodes.get(issue.getIssueCode());
			if (field != null) {
				bindingResult.rejectValue(field, issue.getIssueCode());
			} else {
				bindingResult.reject(issue.getIssueCode());
			}
		}
		return bindingResult.getModel();
	}
}
