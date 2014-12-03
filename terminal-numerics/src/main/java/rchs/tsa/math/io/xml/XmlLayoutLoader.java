package rchs.tsa.math.io.xml;

import java.io.InputStream;

import rchs.tsa.math.ui.xml.AppNode;
import rchs.tsa.math.ui.xml.ComponentNode;
import rchs.tsa.math.ui.xml.ILayoutNode;
import rchs.tsa.math.ui.xml.LayoutNode;
import rchs.tsa.math.ui.xml.LayoutType;
import rchs.tsa.math.ui.xml.builder.ILayoutBuilder;
import rchs.tsa.math.ui.xml.builder.LayoutBuilder;
import net.anasa.util.Checks;
import net.anasa.util.Listing;
import net.anasa.util.StringHelper;
import net.anasa.util.data.FormatException;
import net.anasa.util.data.xml.IXmlLoader;
import net.anasa.util.data.xml.XmlElement;
import net.anasa.util.data.xml.XmlFile;
import net.anasa.util.data.xml.XmlFile.XmlException;

public class XmlLayoutLoader implements IXmlLoader<ILayoutNode>
{
	private final Listing<ILayoutBuilder> builders = new Listing<>();
	
	public XmlLayoutLoader()
	{
		add(new LayoutBuilder("component", (element) -> new ComponentNode(element.getIDAttribute(), getProperties(element))));
		add(new LayoutBuilder("app", (element) -> new AppNode(element.getIDAttribute(), getProperties(element))));
		add(new LayoutBuilder("layout", (element) -> {
			LayoutType type = LayoutType.valueOf(StringHelper.upperCase(element.getAttribute("type")));
			Checks.checkNotNull(type, new FormatException("Invalid layout type: " + element.getAttribute("type")));
			LayoutNode node = new LayoutNode(type.getLayout(getProperties(element)));
			for(XmlElement child : element.getElements())
			{
				node.add(load(child), child.getAttribute("pos"));
			}
			return node;
		}));
	}
	
	private void add(ILayoutBuilder builder)
	{
		getBuilders().add(builder);
	}
	
	public Listing<ILayoutBuilder> getBuilders()
	{
		return builders;
	}
	
	@Override
	public ILayoutNode load(XmlElement element) throws FormatException
	{
		Checks.checkNotNull(element, new FormatException("XML element cannot be null"));
		
		ILayoutBuilder builder = getBuilders().getFirst((parse) -> parse.isValid(element));
		Checks.checkNotNull(builder, new FormatException("Could not find builder for element: " + element.getName()));
		
		return builder.getFrom(element);
	}
	
	public ILayoutNode load(InputStream stream) throws FormatException
	{
		try
		{
			XmlFile xml = XmlFile.read(stream);
			
			return load(xml.getBaseElement());
		}
		catch(XmlException e)
		{
			throw new FormatException(e);
		}
	}
}
