package rchs.tsa.math.module.context;

import java.io.File;

import rchs.tsa.math.TerminalNumerics;
import rchs.tsa.math.module.Dependency;
import rchs.tsa.math.module.IModule;
import rchs.tsa.math.module.IResource;
import rchs.tsa.math.module.app.IApp;
import rchs.tsa.math.standard.IStandard;
import rchs.tsa.math.standard.IStandardModel;
import rchs.tsa.math.util.StateStandards;
import net.anasa.util.Debug;
import net.anasa.util.Listing;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.UI;

public class ModuleContext
{
	private final ModuleRegistry modules = new ModuleRegistry();
	private final AppRegistry apps = new AppRegistry();
	private final ComponentRegistry components = new ComponentRegistry();
	private final ActionRegistry actions = new ActionRegistry();
	
	public ModuleRegistry getModules()
	{
		return modules;
	}
	
	public IModule addModule(IModule module)
	{
		try
		{
			getModules().register(module);
			return module;
		}
		catch(Exception e)
		{
			UI.sendError("Failed to load module: " + module, e);
			return null;
		}
	}
	
	public IModule getModule(String id)
	{
		return getModules().getByID(id);
	}
	
	public AppRegistry getApps()
	{
		return apps;
	}
	
	public IApp addApp(IApp app)
	{
		try
		{
			getApps().register(app);
			return app;
		}
		catch(Exception e)
		{
			UI.sendError("Failed to load app: " + app, e);
			return null;
		}
	}
	
	public IApp getApp(String id)
	{
		return getApps().getByID(id);
	}
	
	public Listing<IResource> getResources()
	{
		return new Listing<IResource>().addAll(getModules().getValues()).addAll(getApps().getValues());
	}
	
	public boolean verifyResources()
	{
		return getResources().checkEach((data) -> verify(data));
	}
	
	public boolean verify(IResource resource)
	{
		for(Dependency dependency : resource.getDependencies())
		{
			IResource dataDependency = dependency.getType().getData(this, dependency.getID());
			
			if(dataDependency == null)
			{
				UI.sendError(resource.getID() + " is missing required " + dependency.getType().getName() + ": " + dependency + " (system may not run as expected)");
				return false;
			}
			else if(!dependency.isCompatible(dataDependency.getVersion()))
			{
				UI.sendError(resource.getID() + " does not meet version requirements for " + dependency.getType() + ": " + dependency + " (installed version is " + resource.getVersion() + ")");
				return false;
			}
		}
		
		return true;
	}
	
	public ComponentRegistry getComponents()
	{
		return components;
	}
	
	public ActionRegistry getActions()
	{
		return actions;
	}
	
	public IStandardModel getStandards(String model)
	{
		return StateStandards.getModel(model);
	}
	
	public IStandard getStandard(String data)
	{
		try
		{
			return StateStandards.getStandard(data);
		}
		catch(FormatException e)
		{
			Debug.err("Failed to parse standard: " + data + " (" + e.getMessage() + ")");
			return null;
		}
	}
	
	public Properties getSettings()
	{
		return TerminalNumerics.getSettings();
	}
	
	public File getDirectory()
	{
		return TerminalNumerics.getDirectory();
	}
}
