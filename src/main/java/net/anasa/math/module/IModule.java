package net.anasa.math.module;

public interface IModule extends IDataEntry
{
	public String getName();
	
	public String getDescription();
	
	public IModuleDelegate getDelegate();
}
