package rchs.tsa.math.ui.xml;

import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;
import rchs.tsa.math.resource.app.IApp;
import rchs.tsa.math.resource.module.context.ModuleContext;
import rchs.tsa.math.ui.app.AppPanelComponent;

public class AppNode implements ILayoutNode
{
	private final String id, ref;
	
	private final Properties config;
	
	public AppNode(String id, String ref, Properties config)
	{
		this.id = id;
		this.ref = ref;
		
		this.config = config;
	}
	
	public String getID()
	{
		return id;
	}
	
	@Override
	public String getRef()
	{
		return ref;
	}
	
	public Properties getConfig()
	{
		return config;
	}
	
	@Override
	public IComponent compile(ModuleContext context)
	{
		IApp app = context.getApp(getID());
		
		return app == null ? null : new AppPanelComponent(app, getConfig());
	}
}
