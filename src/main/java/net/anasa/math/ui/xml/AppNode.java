package net.anasa.math.ui.xml;

import net.anasa.math.module.app.IApp;
import net.anasa.math.module.context.ModuleContext;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;

public class AppNode implements ILayoutNode
{
	private final String id;
	
	private final Properties config;
	
	public AppNode(String id, Properties config)
	{
		this.id = id;
		
		this.config = config;
	}
	
	public String getID()
	{
		return id;
	}
	
	public Properties getConfig()
	{
		return config;
	}

	@Override
	public IComponent compile(ModuleContext context)
	{
		IApp app = context.getApps().getByID(getID());
		return app == null ? null : app.getLaunchComponent(getConfig());
	}
}
