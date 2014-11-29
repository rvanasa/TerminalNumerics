package net.anasa.util.ui;

import javax.swing.JScrollPane;

import net.anasa.util.math.Vector2;

public class ScrollComponent extends UIComponent<JScrollPane> implements ISwingComponent
{
	public ScrollComponent(int width, int height, IComponent inner)
	{
		this(new Vector2(width, height), inner);
	}
	
	public ScrollComponent(Vector2 size, IComponent inner)
	{
		this(inner);
		
		setSize(size);
	}
	
	public ScrollComponent(IComponent inner)
	{
		super(new JScrollPane(inner.getHandle()));
		
		setHorizontalScrollBar(false);
		
		setVerticalIncrement(16);
	}
	
	public void setHorizontalScrollBar(boolean horizontal)
	{
		getHandle().setHorizontalScrollBarPolicy(horizontal ? JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED : JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}
	
	public void setVerticalScrollBar(boolean vertical)
	{
		getHandle().setHorizontalScrollBarPolicy(vertical ? JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED: JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	}
	
	public void setVerticalIncrement(int increment)
	{
		getHandle().getVerticalScrollBar().setUnitIncrement(increment);
	}
}
