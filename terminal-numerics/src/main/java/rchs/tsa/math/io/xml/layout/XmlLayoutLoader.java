package rchs.tsa.math.io.xml.layout;

import java.io.IOException;
import java.io.InputStream;

import net.anasa.util.Checks;
import net.anasa.util.data.FormatException;
import net.anasa.util.data.xml.IXmlLoader;
import net.anasa.util.data.xml.XmlElement;
import net.anasa.util.data.xml.XmlFile;
import net.anasa.util.ui.IComponent;
import rchs.tsa.math.io.xml.layout.node.ILayoutNode;
import rchs.tsa.math.resource.module.ModuleException;
import rchs.tsa.math.resource.module.context.ModuleContext;

public class XmlLayoutLoader implements IXmlLoader<ILayoutNode>
{
	private final ModuleContext context;
	
	public XmlLayoutLoader(ModuleContext context)
	{
		this.context = context;
	}
	
	public ModuleContext getContext()
	{
		return context;
	}
	
	@Override
	public ILayoutNode load(XmlElement element) throws FormatException
	{
		Checks.checkNotNull(element, new FormatException("XML element cannot be null"));
		
		return new ILayoutNode()
		{
			@Override
			public String getRef()
			{
				return element.getAttribute("ref", null);
			}
			
			@Override
			public IComponent compile(ModuleContext context) throws FormatException
			{
				try
				{
					return context.createComponent(element.getName(), element.toProperties());
				}
				catch(ModuleException e)
				{
					throw new FormatException("Could not create component with ID: " + element.getName(), e);
				}
			}
		};
	}
	
	public ILayoutNode load(InputStream stream) throws IOException, FormatException
	{
		XmlFile xml = XmlFile.read(stream);
		
		return load(xml.getBaseElement());
	}
}
