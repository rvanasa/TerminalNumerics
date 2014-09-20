package net.anasa.math.module;

import java.awt.Image;

public interface IModule
{
	public String getID();
	
	public Version getVersion();
	
	public String getName();
	
	public String getDescription();
	
	public Image getIcon();
	
	public ModuleDelegate getDelegate();
}
