package rchs.tsa.math.resource.module;

import rchs.tsa.math.resource.Dependency;
import rchs.tsa.math.resource.Version;

public abstract class AbstractModule implements IModule
{
	private final String id;
	private final Version version;
	
	private final String name;
	private final String description;
	
	private final Dependency[] dependencies;
	
	public AbstractModule(String id, Version version, String name, String description, Dependency... dependencies)
	{
		this.id = id;
		this.version = version;
		
		this.name = name;
		this.description = description;
		
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
	public Dependency[] getDependencies()
	{
		return dependencies;
	}
}
