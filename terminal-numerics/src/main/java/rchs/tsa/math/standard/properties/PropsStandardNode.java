package rchs.tsa.math.standard.properties;

import rchs.tsa.math.standard.IStandardNode;
import net.anasa.util.data.properties.Properties;

public class PropsStandardNode implements IStandardNode
{
	private final Properties props;
	
	public PropsStandardNode(Properties props)
	{
		this.props = props;
	}

	public Properties getProps()
	{
		return props;
	}

	@Override
	public String getID()
	{
		return getProps().getKey().toUpperCase();
	}
	
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + ":" + getID();
	}
}
