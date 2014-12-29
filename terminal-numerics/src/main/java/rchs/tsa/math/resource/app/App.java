package rchs.tsa.math.resource.app;

import java.awt.Image;

import rchs.tsa.math.resource.Dependency;
import rchs.tsa.math.resource.Version;
import rchs.tsa.math.resource.module.context.IComponentEntry;
import rchs.tsa.math.standard.IStandard;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.UI;

public class App implements IApp
{
	private final String id;
	private final Version version;
	private final String name;
	private final String description;
	
	private final IStandard[] standards;
	private final Image icon;
	
	private final IComponentEntry launchComponent;

	private final Dependency[] dependencies;
	
	public App(String id, Version version, String name, String description, IStandard[] standards, Image icon, IComponentEntry launchComponent, Dependency[] dependencies)
	{
		this.id = id;
		this.version = version;
		this.name = name;
		this.description = description;
		
		this.standards = standards;
		this.icon = icon;
		
		this.launchComponent = launchComponent;

		this.dependencies = dependencies;
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
	
	public IComponentEntry getLaunchComponent()
	{
		return launchComponent;
	}

	@Override
	public IComponent getLaunchComponent(Properties props)
	{
		try
		{
			return getLaunchComponent().getComponent(props);
		}
		catch(Exception e)
		{
			UI.sendError("Failed to create launch component for app: " + getID(), e);
			return null;
		}
	}
	
	@Override
	public Dependency[] getDependencies()
	{
		return dependencies;
	}
}
