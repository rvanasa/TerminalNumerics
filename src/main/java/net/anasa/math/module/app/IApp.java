package net.anasa.math.module.app;

import java.awt.Image;

import net.anasa.math.module.IResource;
import net.anasa.math.module.ResourceType;
import net.anasa.math.standard.IStandard;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;

public interface IApp extends IResource
{
	@Override
	default ResourceType getResourceType()
	{
		return ResourceType.APPS;
	}
	
	public String getName();
	
	public String getDescription();
	
	public IStandard[] getStandards();
	
	public Image getIcon();
	
	public IComponent getLaunchComponent(Properties props);
}
