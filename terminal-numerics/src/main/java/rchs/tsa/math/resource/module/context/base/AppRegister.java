package rchs.tsa.math.resource.module.context.base;

import rchs.tsa.math.resource.app.IApp;
import rchs.tsa.math.resource.module.ModuleException;
import rchs.tsa.math.resource.module.context.IRegister;
import net.anasa.util.Checks;
import net.anasa.util.Debug;
import net.anasa.util.Listing;
import net.anasa.util.StringHelper;

public class AppRegister implements IRegister<IApp>
{
	private final Listing<IApp> apps = new Listing<>();
	
	public AppRegister()
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
		
		Debug.log("Loaded app: " + app.getID() + " " + app.getVersion());
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
