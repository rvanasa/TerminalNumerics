package net.anasa.util.ui;

import javax.swing.JScrollPane;

public class ScrollComponent extends UIComponent<JScrollPane> implements ISwingComponent
{
	public ScrollComponent(IComponent inner)
	{
		super(new JScrollPane(inner.getHandle()));
		
		setHorizontalScrollBar(false);
	}
	
	public void setHorizontalScrollBar(boolean horizontal)
	{
		getHandle().setHorizontalScrollBarPolicy(horizontal ? JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED : JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}
	
	public void setVerticalScrollBar(boolean vertical)
	{
		getHandle().setHorizontalScrollBarPolicy(vertical ? JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED: JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	}
}
