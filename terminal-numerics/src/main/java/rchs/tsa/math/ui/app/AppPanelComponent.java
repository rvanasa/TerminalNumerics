package rchs.tsa.math.ui.app;

import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.UI;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;
import rchs.tsa.math.resource.app.IApp;
import rchs.tsa.math.resource.module.ModuleException;

public class AppPanelComponent extends PanelComponent
{
	private final IApp app;
	
	private final IComponent launchComponent;
	
	public AppPanelComponent(IApp app, Properties config)
	{
		this.app = app;
		
		IComponent component = null;
		
		try
		{
			component = app.getLaunchComponent(config);
		}
		catch(ModuleException e)
		{
			Throwable cause = e;
			while(cause.getCause() != null)
			{
				cause = cause.getCause();
			}
			
			UI.sendError("Failed to launch app: " + getApp().getID(), cause);
		}
		
		this.launchComponent = component;
		
		new UIBorderLayout()
				.set(BorderPosition.CENTER, launchComponent)
				.apply(this);
	}
	
	public IApp getApp()
	{
		return app;
	}
	
	public IComponent getLaunchComponent()
	{
		return launchComponent;
	}
}
