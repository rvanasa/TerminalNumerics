package rchs.tsa.math.ui.app;

import java.awt.Image;

import javax.swing.ImageIcon;

import net.anasa.util.data.properties.Properties;
import net.anasa.util.ui.ContextMenuComponent;
import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.WindowComponent;
import net.anasa.util.ui.event.ClickEvent.ButtonType;
import net.anasa.util.ui.menu.MenuActionComponent;
import rchs.tsa.math.TerminalNumerics;
import rchs.tsa.math.resource.app.IApp;

public class AppIconComponent extends LabelComponent
{
	private static final int IMAGE_SIZE = 32;
	
	private final IApp app;
	
	private final Properties launchConfig;
	
	public AppIconComponent(IApp app)
	{
		this(app, new Properties());
	}
	
	public AppIconComponent(IApp app, Properties launchConfig)
	{
		super(app.getIcon() == null ? new ImageIcon(TerminalNumerics.getIcon()) : new ImageIcon(app.getIcon().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH)));
		
		this.app = app;
		
		this.launchConfig = launchConfig;
		
		setCursor(CursorType.HAND);
		addClickListener(ButtonType.LEFT, (event) -> launchApp());
		
		setBorder(2, 2);
		
		setContextMenu(new ContextMenuComponent(new IComponent[] {
				new MenuActionComponent("Launch app", this::launchApp),
				new MenuActionComponent("View info", () -> new WindowComponent(app.getName(), app.getIcon() != null ? app.getIcon() : TerminalNumerics.getIcon(), new AppInfoComponent(app)).setResizable(false).display()),
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
		WindowComponent window = new WindowComponent(getApp().getName(), getApp().getIcon() != null ? getApp().getIcon() : TerminalNumerics.getIcon(), new AppPanelComponent(getApp(), getLaunchConfig()));
		window.display();
		window.setMinSize(window.getSize());
	}
}
