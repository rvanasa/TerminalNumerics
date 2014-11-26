package net.anasa.math.ui.xml;

import net.anasa.math.module.app.IApp;
import net.anasa.math.module.context.ModuleContext;
import net.anasa.util.ui.IComponent;

public class AppNode implements ILayoutNode
{
	private final String id;
	
	public AppNode(String id)
	{
		this.id = id;
	}

	public String getID()
	{
		return id;
	}
	
	@Override
	public IComponent compile(ModuleContext context)
	{
		IApp app = context.getApps().getByID(getID());
		return app == null ? null : app.getLaunchComponent();
	}
}
