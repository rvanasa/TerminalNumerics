package rchs.tsa.math.ui.xml;

import net.anasa.util.data.FormatException;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;
import rchs.tsa.math.resource.module.ModuleException;
import rchs.tsa.math.resource.module.context.ModuleContext;

public class ComponentNode implements ILayoutNode
{
	private final String id;
	private final Properties props;
	
	public ComponentNode(String id, Properties props)
	{
		this.id = id;
		this.props = props;
	}

	public String getID()
	{
		return id;
	}

	public Properties getProps()
	{
		return props;
	}
	
	@Override
	public IComponent compile(ModuleContext context) throws FormatException
	{
		try
		{
			return context.createComponent(getID(), getProps());
		}
		catch(ModuleException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
