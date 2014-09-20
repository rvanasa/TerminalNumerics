package net.anasa.util.ui;

import java.awt.Container;

public abstract class UIParentComponent<T extends Container> extends UIComponent<T> implements IParentComponent
{
	public UIParentComponent(T handle)
	{
		super(handle);
	}
	
	@Override
	public void setComponent(IComponent component, String pos)
	{
		if(component != null)
		{
			getHandle().add(component.getHandle(), pos);
		}
	}
	
	@Override
	public void addComponent(IComponent component)
	{
		if(component != null)
		{
			getHandle().add(component.getHandle());
		}
	}
}
