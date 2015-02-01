package rchs.tsa.math.io.xml.layout;

import net.anasa.util.StringHelper;
import net.anasa.util.data.FormatException;
import net.anasa.util.data.IConformHandler;
import net.anasa.util.data.xml.XmlElement;
import rchs.tsa.math.io.xml.layout.node.ILayoutNode;

public class LayoutBuilder implements ILayoutBuilder
{
	private final String id;
	
	private final IConformHandler<XmlElement, ILayoutNode> handle;
	
	public LayoutBuilder(String id, IConformHandler<XmlElement, ILayoutNode> handle)
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
}
