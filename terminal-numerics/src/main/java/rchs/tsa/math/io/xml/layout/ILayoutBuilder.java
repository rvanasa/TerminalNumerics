package rchs.tsa.math.io.xml.layout;

import rchs.tsa.math.io.xml.layout.node.ILayoutNode;
import net.anasa.util.data.FormatException;
import net.anasa.util.data.xml.XmlElement;

public interface ILayoutBuilder
{
	public boolean isValid(XmlElement element);
	
	public ILayoutNode getFrom(XmlElement element) throws FormatException;
}
