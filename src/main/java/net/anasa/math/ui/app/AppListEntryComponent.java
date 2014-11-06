package net.anasa.math.ui.app;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;

import net.anasa.math.module.ModuleException;
import net.anasa.math.module.app.IApp;
import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
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
		
		UIBorderLayout layout = new UIBorderLayout(4);
		layout.set(BorderPosition.LEFT, icon);
		layout.set(BorderPosition.CENTER, details);
		layout.apply(this);
		
		getHandle().setBorder(BorderFactory.createEtchedBorder());
		getHandle().setMinimumSize(new Dimension(100, 40));
		getHandle().setMaximumSize(new Dimension(400, 40));
	}
	
	public IApp getApp()
	{
		return app;
	}
}
