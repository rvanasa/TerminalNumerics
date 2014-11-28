package net.anasa.math.module.context;

import net.anasa.math.module.ModuleException;
import net.anasa.math.module.app.IApp;
import net.anasa.util.Checks;
import net.anasa.util.Debug;
import net.anasa.util.Listing;
import net.anasa.util.StringHelper;

public class AppRegistry implements IRegistry<IApp>
{
	private final Listing<IApp> apps = new Listing<>();
	
	public AppRegistry()
	{
		
	}
	
	public Listing<IApp> getValues()
	{
		return apps;
	}
	
	public void register(IApp app) throws ModuleException
	{
		Checks.checkNotNull(app, new ModuleException("App cannot be null"));
		Checks.check(getValues().check((entry) -> !StringHelper.equals(app.getID(), entry.getID())), new ModuleException("Duplicate app ID: " + app.getID()));
		
		Debug.log("Loaded app: " + app.getName() + " " + app.getVersion());
		getValues().add(app);
	}
	
	@Override
	public IApp getByID(String id)
	{
		return getValues().getFirst((app) -> StringHelper.equals(id, app.getID()));
	}
	
	public IApp getByName(String name)
	{
		return getValues().getFirst((app) -> StringHelper.equals(name, app.getName()));
	}
}
