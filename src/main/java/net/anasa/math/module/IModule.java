package net.anasa.math.module;

public interface IModule
{
	public String getID();
	
	public Version getVersion();
	
	public String getName();
	
	public String getDescription();
	
	public IModuleDelegate getDelegate();
}
