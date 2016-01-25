package net.dragberry.expman.web.component.paginator;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

public class DataTableDialect extends AbstractDialect {
	
	private static final String TABLE_PREFIX = "table";

	@Override
	public String getPrefix() {
		return TABLE_PREFIX;
	}
	
	@Override
	public Set<IProcessor> getProcessors() {
		final Set<IProcessor> processors = new HashSet<IProcessor>();
	    processors.add(new TableIdAttrProcessor());
	    return processors;
	}

}
