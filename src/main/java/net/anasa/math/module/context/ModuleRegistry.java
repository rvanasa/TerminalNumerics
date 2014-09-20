package net.anasa.math.module.context;

import net.anasa.math.module.IModule;
import net.anasa.math.module.ModuleException;
import net.anasa.util.Listing;
import net.anasa.util.StringHelper;

public class ModuleRegistry
{
	private final Listing<IModule> modules = new Listing<>();
	
	public ModuleRegistry()
	{
		
	}
	
	public Listing<IModule> getModules()
	{
		return modules;
	}

	public void register(IModule module) throws ModuleException
	{
		getModules().add(module);
		
		try
		{
			module.getDelegate().init();
		}
		catch(Exception e)
		{
			throw new ModuleException("Failed to initialize module: " + module.getID(), e);
		}
	}
	
	public IModule getByID(String id)
	{
		return getModules().getFirst((module) -> StringHelper.equals(id, module.getID()));
	}
	
	public IModule getByName(String name)
	{
		return getModules().getFirst((module) -> StringHelper.equals(name, module.getName()));
	}
}
