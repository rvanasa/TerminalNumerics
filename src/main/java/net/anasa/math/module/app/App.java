package net.anasa.math.module.app;

import java.awt.Image;

import net.anasa.math.module.ModuleException;
import net.anasa.math.module.Version;
import net.anasa.math.module.context.IComponentEntry;
import net.anasa.math.module.context.ModuleContext;
import net.anasa.math.standard.IStandard;
import net.anasa.math.util.UI;
import net.anasa.util.Checks;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;

public class App implements IApp
{
	private final ModuleContext context;
	
	private final String id;
	private final Version version;
	private final String name;
	private final String description;
	
	private final IStandard[] standards;
	private final Image icon;
	
	private final String launchComponent;
	private final Properties launchConfig;
	
	public App(String id, Version version, String name, String description, IStandard[] standards, Image icon, String launchComponent, Properties launchConfig)
	{
		this(ModuleContext.getInstance(), id, version, name, description, standards, icon, launchComponent, launchConfig);
	}
	
	public App(ModuleContext context, String id, Version version, String name, String description, IStandard[] standards, Image icon, String launchComponent, Properties launchConfig)
	{
		this.context = context;
		
		this.id = id;
		this.version = version;
		this.name = name;
		this.description = description;
		
		this.standards = standards;
		this.icon = icon;
		
		this.launchComponent = launchComponent;
		this.launchConfig = launchConfig != null ? launchConfig : new Properties();
	}
	
	public ModuleContext getContext()
	{
		return context;
	}
	
	@Override
	public String getID()
	{
		return id;
	}
	
	@Override
	public Version getVersion()
	{
		return version;
	}
	
	@Override
	public String getName()
	{
		return name;
	}
	
	@Override
	public String getDescription()
	{
		return description;
	}
	
	@Override
	public IStandard[] getStandards()
	{
		return standards;
	}
	
	@Override
	public Image getIcon()
	{
		return icon;
	}
	
	@Override
	public IComponent getLaunchComponent()
	{
		try
		{
			IComponentEntry entry = getContext().getComponents().getByID(getLaunchComponentID());
			Checks.checkNotNull(entry, new ModuleException("Component entry does not exist: " + getLaunchComponentID()));
			
			return entry.getComponent(getLaunchConfig());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			UI.sendError("Failed to launch app: " + getName() + " (" + e + ")");
			
			return null;
		}
	}
	
	public String getLaunchComponentID()
	{
		return launchComponent;
	}
	
	public Properties getLaunchConfig()
	{
		return launchConfig;
	}
}
