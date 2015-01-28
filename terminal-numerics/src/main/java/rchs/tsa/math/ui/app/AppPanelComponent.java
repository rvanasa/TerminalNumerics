package rchs.tsa.math.ui.app;

import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;
import rchs.tsa.math.resource.app.IApp;

public class AppPanelComponent extends PanelComponent
{
	private final IApp app;
	
	private final IComponent launchComponent;
	
	public AppPanelComponent(IApp app, Properties config)
	{
		this.app = app;
		
		this.launchComponent = app.getLaunchComponent(config);
		
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
