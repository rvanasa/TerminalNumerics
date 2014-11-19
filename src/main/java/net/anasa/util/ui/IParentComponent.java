package net.anasa.util.ui;

import java.awt.Container;

public interface IParentComponent extends IComponent
{
	@Override
	public Container getHandle();
	
	public void setComponent(IComponent component, String pos);
	
	public void addComponent(IComponent component);
	
	public void removeComponents();
}
