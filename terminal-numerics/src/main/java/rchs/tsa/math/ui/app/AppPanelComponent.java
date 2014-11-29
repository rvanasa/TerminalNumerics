package rchs.tsa.math.ui.app;

import rchs.tsa.math.module.app.IApp;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;

public class AppPanelComponent extends PanelComponent
{
	private final IApp app;
	
	public AppPanelComponent(IApp app, Properties config)
	{
		this.app = app;
		
		UIBorderLayout layout = new UIBorderLayout();
		layout.set(BorderPosition.CENTER, app.getLaunchComponent(config));
		layout.apply(this);
	}

	public IApp getApp()
	{
		return app;
	}
}
