package rchs.tsa.math.resource.module;

import rchs.tsa.math.resource.Dependency;
import rchs.tsa.math.resource.Version;

public class Module extends AbstractModule
{
	private final IModuleDelegate delegate;
	
	public Module(String id, Version version, String name, String description, Dependency[] dependencies, IModuleDelegate delegate)
	{
		super(id, version, name, description, dependencies);
		
		this.delegate = delegate;
	}
	
	@Override
	public IModuleDelegate getDelegate()
	{
		return delegate;
	}
}
