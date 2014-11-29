package net.anasa.math.module;

public interface IModule extends IResource
{
	@Override
	default ResourceType getResourceType()
	{
		return ResourceType.MODULES;
	}
	
	public String getName();
	
	public String getDescription();
	
	public IModuleDelegate getDelegate();
}
