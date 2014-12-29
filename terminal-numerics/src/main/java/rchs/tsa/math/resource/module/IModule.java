package rchs.tsa.math.resource.module;

import rchs.tsa.math.resource.IGenericResource;

public interface IModule extends IGenericResource
{
	public String getName();
	
	public String getDescription();
	
	public IModuleDelegate getDelegate();
}
