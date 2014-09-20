package net.anasa.math.ui.xml.builder;

import net.anasa.math.ui.xml.ILayoutNode;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.xml.Element;

public interface ILayoutBuilder
{
	public boolean isValid(Element element);
	
	public ILayoutNode getFrom(Element element) throws FormatException;
}
