package net.anasa.math.module.context;

import net.anasa.math.module.IModule;
import net.anasa.math.module.ModuleException;
import net.anasa.math.module.app.IApp;
import net.anasa.math.standard.IStandard;
import net.anasa.util.Debug;
import net.anasa.util.data.DataConform.FormatException;

public class ModuleContext
{
	private static final ModuleContext INSTANCE = new ModuleContext();
	
	public static ModuleContext getInstance()
	{
		return INSTANCE;
	}

	private final ModuleRegistry modules = new ModuleRegistry();
	private final AppRegistry apps = new AppRegistry();
	private final ComponentRegistry components = new ComponentRegistry();
	private final ActionRegistry actions = new ActionRegistry();
	
	public ModuleContext()
	{
		
	}
	
	public ModuleRegistry getModules()
	{
		return modules;
	}
	
	public IModule addModule(IModule module) throws ModuleException
	{
		getModules().register(module);
		return module;
	}
	
	public AppRegistry getApps()
	{
		return apps;
	}

	public IApp addApp(IApp app)
	{
		getApps().register(app);
		return app;
	}
	
	public ComponentRegistry getComponents()
	{
		return components;
	}
	
	public ActionRegistry getActions()
	{
		return actions;
	}
	
	public IStandard getStandard(String data) throws FormatException
	{
		Debug.warn("Standards NYI (" + data + ")");
		return null;
	}
}
