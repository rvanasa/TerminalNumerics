package net.anasa.math.module;

import java.awt.Image;

public abstract class AbstractModule implements IModule
{
	private final String id;
	private final Version version;

	private final String name;
	private final String description;
	
	private final Image icon;
	private final ModuleDelegate delegate;
	
	public AbstractModule(String id, Version version, String name, String description, Image icon, ModuleDelegate delegate)
	{
		this.id = id;
		this.version = version;
		
		this.name = name;
		this.description = description;
		
		this.icon = icon;
		this.delegate = delegate;
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
	public Image getIcon()
	{
		return icon;
	}

	@Override
	public ModuleDelegate getDelegate()
	{
		return delegate;
	}
}
