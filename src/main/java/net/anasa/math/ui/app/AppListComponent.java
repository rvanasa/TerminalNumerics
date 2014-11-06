package net.anasa.math.ui.app;

import net.anasa.math.module.app.IApp;
import net.anasa.math.module.context.ModuleContext;
import net.anasa.math.util.UI;
import net.anasa.util.Listing;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.layout.UIVerticalLayout;

public class AppListComponent extends PanelComponent
{
	public AppListComponent()
	{
		this(ModuleContext.getInstance());
	}
	
	public AppListComponent(ModuleContext context)
	{
		this(context.getApps().getApps());
	}
	
	public AppListComponent(Listing<IApp> apps)
	{
		UIVerticalLayout layout = new UIVerticalLayout();
		layout.setSpacing(4);
		
		for(IApp app : apps)
		{
			try
			{
				layout.add(new AppListEntryComponent(app));
			}
			catch(Exception e)
			{
				UI.sendError("Failed to load app: " + app.getName(), e);
			}
		}
		
		layout.apply(this);
	}
}
