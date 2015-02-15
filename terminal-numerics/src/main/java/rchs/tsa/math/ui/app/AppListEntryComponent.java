package rchs.tsa.math.ui.app;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.BevelBorder;

import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.ScrollComponent;
import net.anasa.util.ui.WindowComponent;
import net.anasa.util.ui.event.ClickEvent.ButtonType;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;
import net.anasa.util.ui.layout.UIVerticalLayout;
import rchs.tsa.math.TerminalNumerics;
import rchs.tsa.math.resource.app.IApp;
import rchs.tsa.math.resource.module.ModuleException;
import rchs.tsa.math.standard.IStandard;
import rchs.tsa.math.ui.standard.StandardDomainComponent;

public class AppListEntryComponent extends PanelComponent
{
	private final IApp app;
	
	public AppListEntryComponent(IApp app) throws ModuleException
	{
		this.app = app;
		
		setBorder(new BevelBorder(BevelBorder.RAISED));
		lockSize(480, 40);
		
		AppIconComponent icon = new AppIconComponent(app);
		
		LabelComponent name = new LabelComponent(app.getName(), new Font(Font.SANS_SERIF, Font.BOLD, 12));
		name.setCursor(CursorType.HAND);
		name.addClickListener((event) -> icon.getEvents().dispatch(event));
		name.setContextMenu(icon.getContextMenu());
		
		LabelComponent description = new LabelComponent(app.getDescription(), new Font(Font.SERIF, Font.PLAIN, 12));
		description.setForeground(new Color(0x222222));
		
		UIVerticalLayout standardsLayout = new UIVerticalLayout();
		for(IStandard standard : getApp().getStandards())
		{
			LabelComponent label = new LabelComponent(standard.getName(), new Font(Font.SANS_SERIF, Font.PLAIN, 10));
			label.setBorder(0, -2);
			label.addClickListener(ButtonType.LEFT, (event) -> new WindowComponent(standard.getName(), TerminalNumerics.getIcon(), new ScrollComponent(640, 600, new StandardDomainComponent(standard.getDomain(), standard))).display());
			label.setCursor(CursorType.HAND);
			
			standardsLayout.add(label);
		}
		
		PanelComponent standards = new PanelComponent(standardsLayout);
		standards.setBorder(24, 0);
		ScrollComponent items = new ScrollComponent(standards);
		items.removeBorder();
		
		new UIBorderLayout(4)
				.set(BorderPosition.LEFT, icon)
				.set(BorderPosition.CENTER, new PanelComponent(new UIBorderLayout()
						.set(BorderPosition.TOP, name)
						.set(BorderPosition.CENTER, description)))
				.set(BorderPosition.RIGHT, items)
				.apply(this);
	}
	
	public IApp getApp()
	{
		return app;
	}
}
