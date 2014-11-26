package net.anasa.math.module.app;

import java.awt.Image;

import net.anasa.math.module.Version;
import net.anasa.math.standard.IStandard;
import net.anasa.util.ui.IComponent;

public class App implements IApp
{
	private final String id;
	private final Version version;
	private final String name;
	private final String description;
	
	private final IStandard[] standards;
	private final Image icon;
	
	private final IComponent launchComponent;
	
	public App(String id, Version version, String name, String description, IStandard[] standards, Image icon, IComponent launchComponent)
	{
		this.id = id;
		this.version = version;
		this.name = name;
		this.description = description;
		
		this.standards = standards;
		this.icon = icon;
		
		this.launchComponent = launchComponent;
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
		return launchComponent;
	}
}
