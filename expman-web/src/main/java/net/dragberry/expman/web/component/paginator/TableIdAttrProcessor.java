package net.dragberry.expman.web.component.paginator;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractTextChildModifierAttrProcessor;

public class TableIdAttrProcessor extends AbstractTextChildModifierAttrProcessor {

	private static final String TABLE_ID = "id";
	
	protected TableIdAttrProcessor() {
		super(TABLE_ID);
	}

	@Override
	protected String getText(Arguments arguments, Element element, String attributeName) {
		return "Table id: " + element.getAttributeValue(attributeName);
	}

	@Override
	public int getPrecedence() {
		return 10000;
	}

}
