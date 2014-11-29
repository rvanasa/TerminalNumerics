package rchs.tsa.math.ui.xml.builder;

import rchs.tsa.math.ui.xml.ILayoutNode;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.xml.XmlElement;

public interface ILayoutBuilder
{
	public boolean isValid(XmlElement element);
	
	public ILayoutNode getFrom(XmlElement element) throws FormatException;
}
