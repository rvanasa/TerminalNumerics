package net.anasa.math.module;

import net.anasa.math.module.context.IComponentEntry;
import net.anasa.math.module.context.ModuleContext;
import net.anasa.util.ICallback;
import net.anasa.util.ui.IComponent;

public interface IModuleDelegate
{
	public default void init() throws Exception
	{
		
	}
	
	public default IComponent getPrimaryComponent() throws ModuleException
	{
		return null;
	}
	
	public default ModuleContext getContext()
	{
		return ModuleContext.getInstance();
	}
	
	public default void addComponent(String id, IComponentEntry entry)
	{
		getContext().getComponents().register(id, entry);
	}
	
	public default void addAction(String id, ICallback callback)
	{
		getContext().getActions().register(id, callback);
	}
}
