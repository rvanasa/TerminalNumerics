package net.anasa.math.ui;

import net.anasa.math.MathSoftware;
import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.ScrollComponent;
import net.anasa.util.ui.TextAreaComponent;

public class LicenseComponent extends PanelComponent
{
	public LicenseComponent()
	{
		TextAreaComponent text = new TextAreaComponent(80, 30);
		text.setEditable(false);
		text.setValue(MathSoftware.getLicense());
		text.setCaretAtStart();
		
		addComponent(new ScrollComponent(text));
	}
}
