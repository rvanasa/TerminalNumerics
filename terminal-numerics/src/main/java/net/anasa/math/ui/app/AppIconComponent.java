package net.anasa.math.ui.app;

import java.awt.Image;

import javax.swing.ImageIcon;

import net.anasa.math.module.ModuleException;
import net.anasa.math.module.app.IApp;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.ContextMenuComponent;
import net.anasa.util.ui.ContextMenuComponent.MenuOption;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.WindowComponent;
import net.anasa.util.ui.event.ClickEvent.ButtonType;

public class AppIconComponent extends LabelComponent
{
	private static final int IMAGE_SIZE = 32;
	
	private final IApp app;
	
	private final Properties launchConfig;
	
	public AppIconComponent(IApp app) throws ModuleException
	{
		this(app, new Properties());
	}
	
	public AppIconComponent(IApp app, Properties launchConfig) throws ModuleException
	{
		super(app.getIcon() == null ? null : new ImageIcon(app.getIcon().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH)));
		
		this.app = app;
		
		this.launchConfig = launchConfig;
		
		setCursor(CursorType.HAND);
		
		addClickListener((event) -> {
			if(event.getButton() == ButtonType.LEFT)
			{
				launchApp();
			}
		});
		
		setBorder(2, 2);
		
		setContextMenu(new ContextMenuComponent(new MenuOption[] {
				new MenuOption("Launch app", this::launchApp),
				new MenuOption("View info", () -> new WindowComponent(app.getName(), app.getIcon(), new AppInfoComponent(app)).display()),
		}));
	}
	
	public IApp getApp()
	{
		return app;
	}
	
	public Properties getLaunchConfig()
	{
		return launchConfig;
	}
	
	public void launchApp()
	{
		WindowComponent window = new WindowComponent(getApp().getName(), getApp().getIcon(), getApp().getLaunchComponent(getLaunchConfig()));
		window.display();
		window.setMinSize(window.getSize());
	}
}
