package rchs.tsa.math.resource.app;

import java.awt.Image;

import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;
import rchs.tsa.math.resource.IGenericResource;
import rchs.tsa.math.standard.IStandard;

public interface IApp extends IGenericResource
{
	public String getName();
	
	public String getDescription();
	
	public IStandard[] getStandards();
	
	public Image getIcon();
	
	public IComponent getLaunchComponent(Properties props);
}
