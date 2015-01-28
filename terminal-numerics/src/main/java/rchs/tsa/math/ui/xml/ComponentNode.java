package rchs.tsa.math.ui.xml;

import net.anasa.util.data.FormatException;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;
import rchs.tsa.math.resource.module.ModuleException;
import rchs.tsa.math.resource.module.context.ModuleContext;

public class ComponentNode implements ILayoutNode
{
	private final String id, ref;
	private final Properties props;
	
	public ComponentNode(String id, String ref, Properties props)
	{
		this.id = id;
		this.ref = ref;
		this.props = props;
	}

	public String getID()
	{
		return id;
	}

	@Override
	public String getRef()
	{
		return ref;
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
