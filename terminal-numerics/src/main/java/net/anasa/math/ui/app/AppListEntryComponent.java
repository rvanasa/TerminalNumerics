package net.anasa.math.ui.app;

import java.awt.Color;

import javax.swing.BorderFactory;

import net.anasa.math.module.ModuleException;
import net.anasa.math.module.app.IApp;
import net.anasa.util.Listing;
import net.anasa.util.StringHelper;
import net.anasa.util.math.Vector2;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.ScrollComponent;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;

public class AppListEntryComponent extends PanelComponent
{
	private final IApp app;
	
	public AppListEntryComponent(IApp app) throws ModuleException
	{
		this.app = app;
		
		AppIconComponent icon = new AppIconComponent(app);
		
		PanelComponent details = new PanelComponent();
		
		LabelComponent name = new LabelComponent(app.getName());
		name.setCursor(CursorType.HAND);
		name.addClickListener((event) -> icon.getEvents().dispatch(event));
		name.setContextMenu(icon.getContextMenu());
		
		LabelComponent description = new LabelComponent(app.getDescription());
		description.setForeground(new Color(0x222222));
		
		UIBorderLayout detailLayout = new UIBorderLayout();
		detailLayout.set(BorderPosition.TOP, name);
		detailLayout.set(BorderPosition.CENTER, description);
		detailLayout.apply(details);
		
		LabelComponent standards = new LabelComponent("<html>" + StringHelper.join("<br/>", new Listing<>(getApp().getStandards())
				.conform((standard) -> standard.getName())));
		standards.setBorder(24, 0);
		ScrollComponent items = new ScrollComponent(standards);
		items.removeBorder();
		
		UIBorderLayout layout = new UIBorderLayout(4);
		layout.set(BorderPosition.LEFT, icon);
		layout.set(BorderPosition.CENTER, details);
		layout.set(BorderPosition.RIGHT, items);
		layout.apply(this);

		setBorder(BorderFactory.createEtchedBorder());
		
		Vector2 size = new Vector2(480, 40);
		setMaxSize(size);
		setSize(size);
	}
	
	public IApp getApp()
	{
		return app;
	}
}
