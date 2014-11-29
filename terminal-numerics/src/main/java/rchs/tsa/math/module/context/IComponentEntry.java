package rchs.tsa.math.module.context;

import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;

public interface IComponentEntry
{
	public IComponent getComponent(Properties props) throws Exception;
}
