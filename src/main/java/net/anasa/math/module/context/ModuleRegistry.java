package net.anasa.math.module.context;

import net.anasa.math.module.IModule;
import net.anasa.math.module.ModuleException;
import net.anasa.util.Checks;
import net.anasa.util.Debug;
import net.anasa.util.Listing;
import net.anasa.util.StringHelper;

public class ModuleRegistry implements IRegistry<IModule>
{
	private final Listing<IModule> modules = new Listing<>();
	
	public ModuleRegistry()
	{
		
	}
	
	public Listing<IModule> getValues()
	{
		return modules;
	}

	public void register(IModule module) throws ModuleException
	{
		Checks.checkNotNull(module, new ModuleException("Module cannot be null"));
		Checks.check(getValues().check((entry) -> !StringHelper.equals(module.getID(), entry.getID())), new ModuleException("Duplicate module ID: " + module.getID()));
		
		getValues().add(module);
		Debug.log("Loaded module: " + module.getName() + " " + module.getVersion());
		
		try
		{
			module.getDelegate().init();
		}
		catch(Exception e)
		{
			throw new ModuleException("Failed to initialize module: " + module.getID(), e);
		}
	}
	
	@Override
	public IModule getByID(String id)
	{
		return getValues().getFirst((module) -> StringHelper.equals(id, module.getID()));
	}
	
	public IModule getByName(String name)
	{
		return getValues().getFirst((module) -> StringHelper.equals(name, module.getName()));
	}
}
