package net.anasa.math.module.context;

import java.io.File;

import net.anasa.math.module.IModule;
import net.anasa.math.module.JarModule;
import net.anasa.math.module.ModuleException;
import net.anasa.util.Debug;
import net.anasa.util.Listing;

public class ModuleContext
{
	private static final ModuleContext INSTANCE = new ModuleContext();
	
	public static ModuleContext getInstance()
	{
		return INSTANCE;
	}
	
	private final ModuleRegistry modules = new ModuleRegistry();
	private final ComponentRegistry components = new ComponentRegistry();
	
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

	public IModule loadModule(File file) throws ModuleException
	{
		IModule module = new JarModule(file);
		getModules().register(module);
		
		Debug.log("Loaded module: " + module);
		
		return module;
	}

	public Listing<IModule> loadModules(File dir) throws ModuleException
	{
		Listing<IModule> modules = new Listing<>();
		
		for(File file : dir.listFiles((path, name) -> name.endsWith(".jar")))
		{
			modules.add(loadModule(file));
		}
		
		return modules;
	}
}
