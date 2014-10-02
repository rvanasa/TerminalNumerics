package net.anasa.util.ui.event;

import net.anasa.util.ui.WindowComponent;

public class WindowCloseEvent extends UIEvent
{
	public WindowCloseEvent(WindowComponent component)
	{
		super(component);
	}
	
	@Override
	public WindowComponent getComponent()
	{
		return (WindowComponent)super.getComponent();
	}
}
