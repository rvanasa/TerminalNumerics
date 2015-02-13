package rchs.tsa.math.ui.app;

import java.awt.Color;

import javax.swing.border.BevelBorder;

import net.anasa.util.Listing;
import net.anasa.util.StringHelper;
import net.anasa.util.math.Vector2;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.ScrollComponent;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;
import rchs.tsa.math.resource.app.IApp;
import rchs.tsa.math.resource.module.ModuleException;

public class AppListEntryComponent extends PanelComponent
{
	private final IApp app;
	
	public AppListEntryComponent(IApp app) throws ModuleException
	{
		this.app = app;
		
		setBorder(new BevelBorder(BevelBorder.RAISED));
		
		Vector2 size = new Vector2(480, 40);
		setMaxSize(size);
		setSize(size);
		
		AppIconComponent icon = new AppIconComponent(app);
		
		PanelComponent details = new PanelComponent();
		
		LabelComponent name = new LabelComponent(app.getName());
		name.setCursor(CursorType.HAND);
		name.addClickListener((event) -> icon.getEvents().dispatch(event));
		name.setContextMenu(icon.getContextMenu());
		
		LabelComponent description = new LabelComponent(app.getDescription());
		description.setForeground(new Color(0x222222));
		
		new UIBorderLayout()
				.set(BorderPosition.TOP, name)
				.set(BorderPosition.CENTER, description)
				.apply(details);
		
		LabelComponent standards = new LabelComponent("<html>" + StringHelper.join("<br/>", new Listing<>(getApp().getStandards())
				.format((standard) -> standard.getName())));
		standards.setBorder(24, 0);
		ScrollComponent items = new ScrollComponent(standards);
		items.removeBorder();
		
		new UIBorderLayout(4)
				.set(BorderPosition.LEFT, icon)
				.set(BorderPosition.CENTER, details)
				.set(BorderPosition.RIGHT, items)
				.apply(this);
	}
	
	public IApp getApp()
	{
		return app;
	}
}
