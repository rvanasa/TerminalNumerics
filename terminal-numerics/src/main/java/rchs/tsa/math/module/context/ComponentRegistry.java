package rchs.tsa.math.module.context;

import rchs.tsa.math.module.ModuleException;
import net.anasa.util.Checks;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;

public class ComponentRegistry extends LookupRegistry<IComponentEntry>
{
	public IComponent create(String id, Properties props) throws ModuleException
	{
		if(props == null)
		{
			props = new Properties();
		}
		
		Checks.checkNotNull(id, "Component ID cannot be null");
		
		IComponentEntry entry = getByID(id);
		Checks.checkNotNull(entry, new ModuleException("Invalid component: " + id));
		
		try
		{
			return entry.getComponent(props);
		}
		catch(Exception e)
		{
			throw new ModuleException("Failed to construct component with properties: " + props, e);
		}
	}
}
