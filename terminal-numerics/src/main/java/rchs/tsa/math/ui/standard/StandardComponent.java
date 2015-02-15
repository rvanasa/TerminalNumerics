package rchs.tsa.math.ui.standard;

import java.awt.Font;

import net.anasa.util.ui.LabelComponent;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.layout.UIBorderLayout;
import net.anasa.util.ui.layout.UIBorderLayout.BorderPosition;
import rchs.tsa.math.standard.IStandard;

public class StandardComponent extends PanelComponent
{
	private final IStandard standard;
	
	public StandardComponent(IStandard standard)
	{
		this.standard = standard;
		
		UIBorderLayout layout = new UIBorderLayout();
		layout.set(BorderPosition.TOP, new LabelComponent(standard.getName(), new Font(Font.DIALOG, Font.BOLD, 11)));
		layout.set(BorderPosition.CENTER, new LabelComponent("<html><div style='width:600'>" + standard.getDescription(), new Font(Font.SANS_SERIF, Font.PLAIN, 11)));
		layout.apply(this);
	}

	public IStandard getStandard()
	{
		return standard;
	}
}
