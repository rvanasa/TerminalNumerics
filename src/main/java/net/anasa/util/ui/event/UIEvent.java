package net.anasa.util.ui.event;

import net.anasa.util.ui.IComponent;

public abstract class UIEvent
{
	private final IComponent component;
	
	public UIEvent(IComponent component)
	{
		this.component = component;
	}

	public IComponent getComponent()
	{
		return component;
	}
}
