package net.dragberry.expman.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class ResultListTO<T extends TransferObject> extends AbstractResultTO<T> {

	private static final long serialVersionUID = -2132631917832579006L;
	
	private List<T> list = new ArrayList<>();
	
	public List<T> getList() {
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		} else {
			return Collections.emptyList();
		}
	}
	
	public void addList(List<T> list) {
		this.list.addAll(list);
	}
	
}
