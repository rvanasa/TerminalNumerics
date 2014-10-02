package net.anasa.math.module;


public abstract class AbstractModule implements IModule, IModuleDelegate
{
	private final String id;
	private final Version version;

	private final String name;
	private final String description;
	
	public AbstractModule(String id, Version version, String name, String description)
	{
		this.id = id;
		this.version = version;
		
		this.name = name;
		this.description = description;
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
	public IModuleDelegate getDelegate()
	{
		return this;
	}
}
