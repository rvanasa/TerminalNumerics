package net.anasa.math.module.context;

import net.anasa.math.module.ModuleException;
import net.anasa.util.Checks;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;

public class ComponentRegistry extends AbstractRegistry<IComponentEntry>
{
	public IComponent create(String id, Properties props) throws ModuleException
	{
		Checks.checkNotNull(id, "Component id cannot be null");
		
		IComponentEntry entry = getByID(id);
		
		if(entry != null)
		{
			try
			{
				return entry.getComponent(props);
			}
			catch(Exception e)
			{
				throw new ModuleException("Failed to construct component with properties: " + props, e);
			}
		}
		else
		{
			throw new ModuleException("Invalid component: " + id);
		}
	}
}
