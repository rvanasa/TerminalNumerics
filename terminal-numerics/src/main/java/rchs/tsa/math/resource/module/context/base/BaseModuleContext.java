package rchs.tsa.math.resource.module.context.base;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collection;

import net.anasa.util.Checks;
import net.anasa.util.Debug;
import net.anasa.util.Listing;
import net.anasa.util.data.FormatException;
import net.anasa.util.data.io.FileHandler;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.UI;
import rchs.tsa.math.resource.Dependency;
import rchs.tsa.math.resource.IGenericResource;
import rchs.tsa.math.resource.ResourceType;
import rchs.tsa.math.resource.app.IApp;
import rchs.tsa.math.resource.module.IModule;
import rchs.tsa.math.resource.module.ModuleException;
import rchs.tsa.math.resource.module.context.IComponentEntry;
import rchs.tsa.math.resource.module.context.IResourceDownloader;
import rchs.tsa.math.resource.module.context.ModuleContext;
import rchs.tsa.math.resource.module.context.base.ActionRegister.IComponentAction;
import rchs.tsa.math.standard.IStandard;
import rchs.tsa.math.standard.IStandardModel;

public class BaseModuleContext implements ModuleContext
{
	private final Properties settings;
	private final File directory;
	
	private final StateStandards standards = new StateStandards();
	
	private final ModuleRegister modules = new ModuleRegister();
	private final AppRegister apps = new AppRegister();
	private final ComponentRegister components = new ComponentRegister();
	private final ActionRegister actions = new ActionRegister();
	
	private final IResourceDownloader downloader;
	
	public BaseModuleContext(File settingsFile) throws ModuleException
	{
		try
		{
			settings = new FileHandler<>(settingsFile, Properties.FORMAT).read();
			directory = new File(settings.getString("directory", settingsFile.getParent()));
			
			if(getSettings().getBoolean("resource_download", false))
			{
				downloader = new WebResourceDownloader(settings.getString("resource_url"));
			}
			else
			{
				downloader = null;
			}
		}
		catch(Exception e)
		{
			throw new ModuleException(e);
		}
	}
	
	@Override
	public Properties getSettings()
	{
		return settings;
	}
	
	@Override
	public File getDirectory()
	{
		return directory;
	}
	
	@Override
	public StateStandards getStandards()
	{
		return standards;
	}
	
	@Override
	public IStandardModel getStandards(String model)
	{
		return getStandards().getModel(model);
	}
	
	@Override
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
	
	@Override
	public Listing<IModule> getModules()
	{
		return modules.getValues();
	}
	
	@Override
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
	
	@Override
	public IModule getModule(String id)
	{
		return modules.getByID(id);
	}
	
	@Override
	public Listing<IApp> getApps()
	{
		return apps.getValues();
	}
	
	@Override
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
	
	@Override
	public IApp getApp(String id)
	{
		return apps.getByID(id);
	}
	
	@Override
	public Listing<IGenericResource> getResources()
	{
		return new Listing<IGenericResource>().addAll(getModules().getValues()).addAll(getApps().getValues());
	}
	
	@Override
	public boolean verifyResources()
	{
		return getResources().checkEach((data) -> verifyResource(data));
	}
	
	@Override
	public boolean verifyResource(IGenericResource resource)
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
	
	@Override
	public Collection<IComponentEntry> getComponents()
	{
		return components.getEntries().getValues();
	}
	
	@Override
	public IComponentEntry getComponent(String id)
	{
		return components.getByID(id);
	}
	
	@Override
	public void addComponentEntry(String id, IComponentEntry component)
	{
		components.register(id, component);
	}
	
	@Override
	public IComponent createComponent(String id, Properties props) throws ModuleException
	{
		return components.create(id, props);
	}
	
	@Override
	public Collection<IComponentAction> getActions()
	{
		return actions.getEntries().getValues();
	}
	
	@Override
	public IComponentAction getAction(String id)
	{
		return actions.getByID(id);
	}
	
	@Override
	public void addAction(String id, IComponentAction action)
	{
		actions.register(id, action);
	}
	
	@Override
	public void onAction(String data, IComponent component)
	{
		actions.onAction(actions.getIDFromData(data), component, actions.getArgsFromData(data));
	}
	
	@Override
	public IResourceDownloader getDownloader()
	{
		return downloader;
	}
	
	@Override
	public void downloadResource(String id, ResourceType type) throws ModuleException
	{
		Checks.checkNotNull(getDownloader(), new ModuleException("Resource downloader is not configured"));
		
		if(!id.endsWith("." + type.getExtension()))
		{
			id += "." + type.getExtension();
		}
		
		try(InputStream input = getDownloader().downloadResource(id, type))
		{
			File target = new File(getDirectory(), "resources/" + "/" + type.getPath() + "/" + id);
			Files.copy(input, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			type.register(this, target);
		}
		catch(IOException e)
		{
			throw new ModuleException(e);
		}
	}
}
