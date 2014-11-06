package net.anasa.math.ui.app;

import javax.swing.ImageIcon;

import net.anasa.math.module.app.IApp;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.layout.UIVerticalLayout;

public class AppInfoComponent extends PanelComponent
{
	private final IApp app;
	
	public AppInfoComponent(IApp app)
	{
		this.app = app;
		
		setBorder(4, 4);
		
		UIVerticalLayout layout = new UIVerticalLayout(2);
		layout.add(new LabelComponent("ID: " + app.getID()));
		layout.add(new LabelComponent("Name: " + app.getName()));
		layout.add(new LabelComponent("Version: " + app.getVersion()));
		layout.add(new LabelComponent("Description: " + app.getDescription()));
		layout.add(new LabelComponent(new ImageIcon(app.getIcon())));
		layout.apply(this);
	}
	
	public IApp getApp()
	{
		return app;
	}
}
