package rchs.tsa.math.resource.module.context.base;

import net.anasa.util.Checks;
import net.anasa.util.Debug;
import net.anasa.util.Listing;
import net.anasa.util.StringHelper;
import rchs.tsa.math.resource.module.IModule;
import rchs.tsa.math.resource.module.ModuleException;
import rchs.tsa.math.resource.module.context.IRegister;

public class ModuleRegister implements IRegister<IModule>
{
	private final Listing<IModule> modules = new Listing<>();
	
	public ModuleRegister()
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
		Debug.log("Loaded module: " + module.getID() + " " + module.getVersion());
		
		try
		{
			module.getDelegate().init();
		}
		catch(Throwable e)
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
