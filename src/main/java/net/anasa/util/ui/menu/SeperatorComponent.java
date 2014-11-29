package net.anasa.util.ui.menu;

import javax.swing.JSeparator;

import net.anasa.util.ui.ISwingComponent;
import net.anasa.util.ui.UIComponent;

public class SeperatorComponent extends UIComponent<JSeparator> implements ISwingComponent
{
	public SeperatorComponent()
	{
		super(new JSeparator());
	}
}
