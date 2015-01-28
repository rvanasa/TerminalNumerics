package rchs.tsa.math.resource.module.context;

import net.anasa.util.data.properties.Properties;

public interface IComponentEntry
{
	public IComponentHandler getHandler(Properties props) throws Exception;
}
