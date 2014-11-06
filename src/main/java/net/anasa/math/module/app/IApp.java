package net.anasa.math.module.app;

import java.awt.Image;

import net.anasa.math.module.Version;
import net.anasa.math.standard.IStandard;
import net.anasa.util.ui.IComponent;

public interface IApp
{
	public String getID();
	
	public Version getVersion();
	
	public String getName();
	
	public String getDescription();
	
	public IStandard[] getStandards();
	
	public Image getIcon();
	
	public IComponent getLaunchComponent();
}
