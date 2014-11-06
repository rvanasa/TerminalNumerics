package net.anasa.math.module.context;

import net.anasa.math.module.ModuleException;
import net.anasa.math.module.app.IApp;
import net.anasa.util.Checks;
import net.anasa.util.Listing;
import net.anasa.util.StringHelper;

public class AppRegistry implements IRegistry<IApp>
{
	private final Listing<IApp> apps = new Listing<>();
	
	public AppRegistry()
	{
		
	}
	
	public Listing<IApp> getApps()
	{
		return apps;
	}

	public void register(IApp module) throws ModuleException
	{
		Checks.checkNotNull(module, new ModuleException("App cannot be null"));
		
		getApps().add(module);
	}
	
	@Override
	public IApp getByID(String id)
	{
		return getApps().getFirst((app) -> StringHelper.equals(id, app.getID()));
	}
	
	public IApp getByName(String name)
	{
		return getApps().getFirst((app) -> StringHelper.equals(name, app.getName()));
	}
}
