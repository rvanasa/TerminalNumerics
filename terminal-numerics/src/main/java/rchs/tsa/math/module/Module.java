package rchs.tsa.math.module;

public class Module implements IModule, IModuleDelegate
{
	private final String id;
	private final Version version;
	
	private final String name;
	private final String description;
	
	private final Dependency[] dependencies;
	
	public Module(String id, Version version, String name, String description, Dependency... dependencies)
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
	public IModuleDelegate getDelegate()
	{
		return this;
	}

	@Override
	public Dependency[] getDependencies()
	{
		return dependencies;
	}
}
