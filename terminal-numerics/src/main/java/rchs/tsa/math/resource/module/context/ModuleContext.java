package rchs.tsa.math.resource.module.context;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collection;

import net.anasa.util.Debug;
import net.anasa.util.Listing;
import net.anasa.util.data.FormatException;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IActionComponent;
import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.UI;
import rchs.tsa.math.TerminalNumerics;
import rchs.tsa.math.resource.Dependency;
import rchs.tsa.math.resource.IGenericResource;
import rchs.tsa.math.resource.ResourceType;
import rchs.tsa.math.resource.app.IApp;
import rchs.tsa.math.resource.module.IModule;
import rchs.tsa.math.resource.module.ModuleException;
import rchs.tsa.math.resource.module.context.ActionRegister.IComponentAction;
import rchs.tsa.math.standard.IStandard;
import rchs.tsa.math.standard.IStandardModel;

public class ModuleContext
{
	private final StateStandards standards = new StateStandards();
	
	private final ModuleRegister modules = new ModuleRegister();
	private final AppRegister apps = new AppRegister();
	private final ComponentRegister components = new ComponentRegister();
	private final ActionRegister actions = new ActionRegister();
	
	public StateStandards getStandards()
	{
		return standards;
	}
	
	public IStandardModel getStandards(String model)
	{
		return getStandards().getModel(model);
	}
	
	public IStandard getStandard(String data)
	{
		try
		{
			return getStandards().getStandard(data);
		}
		catch(FormatException e)
		{
			Debug.err("Failed to parse standard: " + data + " (" + e.getMessage() + ")");
			return null;
		}
	}
	
	public Listing<IModule> getModules()
	{
		return modules.getValues();
	}
	
	public IModule addModule(IModule module)
	{
		try
		{
			modules.register(module);
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
		return modules.getByID(id);
	}
	
	public Listing<IApp> getApps()
	{
		return apps.getValues();
	}
	
	public IApp addApp(IApp app)
	{
		try
		{
			apps.register(app);
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
		return apps.getByID(id);
	}
	
	public Listing<IGenericResource> getResources()
	{
		return new Listing<IGenericResource>().addAll(getModules().getValues()).addAll(getApps().getValues());
	}
	
	public boolean verifyResources()
	{
		return getResources().checkEach((data) -> verify(data));
	}
	
	public boolean verify(IGenericResource resource)
	{
		for(Dependency dependency : resource.getDependencies())
		{
			IGenericResource dataDependency = dependency.getType().getData(this, dependency.getID());
			
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
	
	public Collection<IComponentEntry> getComponents()
	{
		return components.getEntries().getValues();
	}
	
	public IComponentEntry getComponent(String id)
	{
		return components.getByID(id);
	}
	
	public void addComponent(String id, IComponentEntry component)
	{
		components.register(id, component);
	}
	
	public IComponent createComponent(String id, Properties props) throws ModuleException
	{
		return components.create(id, props);
	}
	
	public Collection<IComponentAction> getActions()
	{
		return actions.getEntries().getValues();
	}
	
	public IComponentAction getAction(String id)
	{
		return actions.getByID(id);
	}
	
	public void addAction(String id, IComponentAction action)
	{
		actions.register(id, action);
	}
	
	public void onAction(String id, IActionComponent component)
	{
		actions.onAction(id, component);
	}
	
	public Properties getSettings()
	{
		return TerminalNumerics.getSettings();
	}
	
	public File getDirectory()
	{
		return TerminalNumerics.getDirectory();
	}
	
	public void downloadResource(URL url, String id, ResourceType type) throws ModuleException
	{
		try(InputStream input = url.openStream())
		{
			File target = new File(getDirectory(), "resources/" + id + "." + type.getExtension());
			Files.copy(input, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			type.register(this, target);
		}
		catch(Exception e)
		{
			throw new ModuleException(e);
		}
	}
}
