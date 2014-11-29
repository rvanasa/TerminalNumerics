package rchs.tsa.math.ui.xml.builder;

import rchs.tsa.math.ui.xml.ILayoutNode;
import net.anasa.util.StringHelper;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.xml.XmlElement;

public class LayoutBuilder implements ILayoutBuilder
{
	private final String id;
	
	private final ILayoutBuilderHandle handle;
	
	public LayoutBuilder(String id, ILayoutBuilderHandle handle)
	{
		this.id = id;
		
		this.handle = handle;
	}

	public String getID()
	{
		return id;
	}
	
	@Override
	public boolean isValid(XmlElement element)
	{
		return StringHelper.equalsIgnoreCase(getID(), element.getName());
	}

	@Override
	public ILayoutNode getFrom(XmlElement element) throws FormatException
	{
		return handle.getFrom(element);
	}
	
	public interface ILayoutBuilderHandle
	{
		public ILayoutNode getFrom(XmlElement element) throws FormatException;
	}
}
