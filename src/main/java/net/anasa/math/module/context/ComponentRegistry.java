package net.anasa.math.module.context;

import net.anasa.math.module.ModuleException;
import net.anasa.util.Checks;
import net.anasa.util.Mapping;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;

public class ComponentRegistry
{
	private final Mapping<String, IComponentEntry> entries = new Mapping<>();
	
	public ComponentRegistry()
	{
		
	}
	
	public Mapping<String, IComponentEntry> getEntries()
	{
		return entries;
	}

	public void register(String id, IComponentEntry entry)
	{
		Checks.checkNotNull(id, "Component id cannot be null");
		Checks.checkNotNull(entry, "Component entry cannot be null");
		
		getEntries().put(id.toLowerCase(), entry);
	}
	
	public IComponent create(String id, Properties props) throws ModuleException
	{
		Checks.checkNotNull(id, "Component id cannot be null");
		
		IComponentEntry entry = getEntries().get(id);
		
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
