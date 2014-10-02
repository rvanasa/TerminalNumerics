package net.anasa.math.module.context;

import net.anasa.math.module.IModule;
import net.anasa.math.module.ModuleException;
import net.anasa.util.Debug;

public class ModuleContext
{
	public static final String PROGRESS_MODULE = "moduleProgress";
	
	private static final ModuleContext INSTANCE = new ModuleContext();
	
	public static ModuleContext getInstance()
	{
		return INSTANCE;
	}
	
	private final ModuleRegistry modules = new ModuleRegistry();
	private final ComponentRegistry components = new ComponentRegistry();
	private final ActionRegistry actions = new ActionRegistry();
	
	private ModuleContext()
	{
		
	}
	
	public ModuleRegistry getModules()
	{
		return modules;
	}
	
	public ComponentRegistry getComponents()
	{
		return components;
	}
	
	public ActionRegistry getActions()
	{
		return actions;
	}
	
	public IModule addModule(IModule module) throws ModuleException
	{
		getModules().register(module);
		Debug.log("Loaded module: " + module.getName() + " " + module.getVersion());
		return module;
	}
}
