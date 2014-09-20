package net.anasa.math.ui.layout.xml;

import net.anasa.math.module.ModuleException;
import net.anasa.math.module.context.ModuleContext;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;

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
	public IComponent compile() throws FormatException
	{
		try
		{
			return ModuleContext.getInstance().getComponents().create(getID(), getProps());
		}
		catch(ModuleException e)
		{
			throw new FormatException(e);
		}
	}
}
