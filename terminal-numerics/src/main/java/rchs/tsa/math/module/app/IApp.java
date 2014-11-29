package rchs.tsa.math.module.app;

import java.awt.Image;

import rchs.tsa.math.module.IResource;
import rchs.tsa.math.module.ResourceType;
import rchs.tsa.math.standard.IStandard;
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
