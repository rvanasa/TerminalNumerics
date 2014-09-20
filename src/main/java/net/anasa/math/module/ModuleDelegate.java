package net.anasa.math.module;

import net.anasa.math.module.context.IComponentEntry;
import net.anasa.math.module.context.ModuleContext;
import net.anasa.util.ui.IComponent;

public abstract class ModuleDelegate
{
	public void init() throws Exception
	{
		
	}
	
	public IComponent getPrimaryComponent() throws ModuleException
	{
		return null;
	}
	
	public ModuleContext getContext()
	{
		return ModuleContext.getInstance();
	}
	
	public void addComponent(String id, IComponentEntry entry)
	{
		getContext().getComponents().register(id, entry);
	}
}
