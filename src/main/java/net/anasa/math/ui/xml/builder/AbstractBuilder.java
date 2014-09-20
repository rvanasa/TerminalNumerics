package net.anasa.math.ui.xml.builder;

import net.anasa.math.ui.xml.ILayoutNode;
import net.anasa.util.StringHelper;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.xml.Element;

public class AbstractBuilder implements ILayoutBuilder
{
	private final String id;
	
	private final ILayoutBuilderHandle handle;
	
	public AbstractBuilder(String id, ILayoutBuilderHandle handle)
	{
		this.id = id;
		
		this.handle = handle;
	}

	public String getID()
	{
		return id;
	}
	
	@Override
	public boolean isValid(Element element)
	{
		return StringHelper.equalsIgnoreCase(getID(), element.getName());
	}

	@Override
	public ILayoutNode getFrom(Element element) throws FormatException
	{
		return handle.getFrom(element);
	}
	
	public interface ILayoutBuilderHandle
	{
		public ILayoutNode getFrom(Element element) throws FormatException;
	}
}
