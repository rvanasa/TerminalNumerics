package net.anasa.math.module.app;

import java.awt.Image;

import net.anasa.math.module.IDataEntry;
import net.anasa.math.standard.IStandard;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;

public interface IApp extends IDataEntry
{
	public String getName();
	
	public String getDescription();
	
	public IStandard[] getStandards();
	
	public Image getIcon();
	
	public IComponent getLaunchComponent(Properties props);
}
