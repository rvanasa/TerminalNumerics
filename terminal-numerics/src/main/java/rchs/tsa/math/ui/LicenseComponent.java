package rchs.tsa.math.ui;

import net.anasa.util.ui.PanelComponent;
import net.anasa.util.ui.ScrollComponent;
import net.anasa.util.ui.TextAreaComponent;
import rchs.tsa.math.TerminalNumerics;

public class LicenseComponent extends PanelComponent
{
	public LicenseComponent()
	{
		TextAreaComponent text = new TextAreaComponent(40, 30);
		text.setEditable(false);
		text.setValue(TerminalNumerics.getLicense());
		text.setCaretAtStart();
		
		addComponent(new ScrollComponent(text));
	}
}
