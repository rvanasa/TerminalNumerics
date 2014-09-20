package net.anasa.util.ui.event;

import java.awt.Graphics2D;

import net.anasa.util.ui.IComponent;

public class DrawEvent extends UIEvent
{
	private final Graphics2D graphics;
	
	public DrawEvent(IComponent component, Graphics2D graphics)
	{
		super(component);
		
		this.graphics = graphics;
	}

	public Graphics2D getGraphics()
	{
		return graphics;
	}
}
