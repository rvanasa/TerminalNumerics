package rchs.tsa.math.resource.module.context;

import java.io.File;
import java.util.Collection;

import net.anasa.util.Listing;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;
import rchs.tsa.math.resource.IGenericResource;
import rchs.tsa.math.resource.ResourceType;
import rchs.tsa.math.resource.app.IApp;
import rchs.tsa.math.resource.module.IModule;
import rchs.tsa.math.resource.module.ModuleException;
import rchs.tsa.math.resource.module.context.base.ActionRegister.IComponentAction;
import rchs.tsa.math.resource.module.context.base.StateStandards;
import rchs.tsa.math.standard.IStandard;
import rchs.tsa.math.standard.IStandardModel;

public interface ModuleContext
{
	public Properties getSettings();
	
	public File getDirectory();
	
	public StateStandards getStandards();
	
	public IStandardModel getStandards(String model);
	
	public IStandard getStandard(String data);
	
	public Listing<IModule> getModules();
	
	public IModule addModule(IModule module);
	
	public IModule getModule(String id);
	
	public Listing<IApp> getApps();
	
	public IApp addApp(IApp app);
	
	public IApp getApp(String id);
	
	public Listing<IGenericResource> getResources();
	
	public boolean verifyResources();
	
	public boolean verifyResource(IGenericResource resource);
	
	public Collection<IComponentEntry> getComponents();
	
	public IComponentEntry getComponent(String id);
	
	public void addComponent(String id, IComponentEntry component);
	
	public IComponent createComponent(String id, Properties props) throws ModuleException;
	
	public Collection<IComponentAction> getActions();
	
	public IComponentAction getAction(String id);
	
	public void addAction(String id, IComponentAction action);
	
	public void onAction(String data, IComponent component);
	
	public IResourceDownloader getDownloader();
	
	public void downloadResource(String id, ResourceType type) throws ModuleException;
}
