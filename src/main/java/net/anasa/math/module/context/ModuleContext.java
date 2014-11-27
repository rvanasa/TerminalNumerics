package net.anasa.math.module.context;

import java.io.File;

import net.anasa.math.MathSoftware;
import net.anasa.math.module.IModule;
import net.anasa.math.module.ModuleException;
import net.anasa.math.module.app.IApp;
import net.anasa.math.standard.IStandard;
import net.anasa.math.standard.IStandardModel;
import net.anasa.math.util.StateStandards;
import net.anasa.util.Checks;
import net.anasa.util.Debug;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.properties.Properties;

public class ModuleContext
{
	public ModuleContext()
	{
		
	}
	
	private final ModuleRegistry modules = new ModuleRegistry();
	private final AppRegistry apps = new AppRegistry();
	private final ComponentRegistry components = new ComponentRegistry();
	private final ActionRegistry actions = new ActionRegistry();
	
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
	
	public IApp addApp(IApp app) throws ModuleException
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
	
	public IStandardModel getStandards() throws ModuleException
	{
		return Checks.checkNotNull(StateStandards.getModel(getStandardsState()), new ModuleException("State is not registered: " + getStandardsState()));
	}
	
	public IStandard getStandard(String data)
	{
		try
		{
			return StateStandards.getStandard(data);
		}
		catch(FormatException e)
		{
			Debug.err("Failed to parse state standard: " + data + " (" + e + ")");
			return null;
		}
	}
	
	public Properties getSettings()
	{
		return MathSoftware.getSettings();
	}
	
	public File getDirectory()
	{
		return MathSoftware.getDirectory();
	}
	
	public String getStandardsState()
	{
		return getSettings().getString("standards.state", "CO");
	}
}
