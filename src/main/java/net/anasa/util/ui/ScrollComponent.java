package net.anasa.util.ui;

import javax.swing.JScrollPane;

public class ScrollComponent extends UIComponent<JScrollPane> implements ISwingComponent
{
	public ScrollComponent(IComponent inner)
	{
		super(new JScrollPane(inner.getHandle()));
	}
}
