package net.anasa.util.ui.layout;

import java.awt.LayoutManager;

import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.IParentComponent;

public abstract class UILayout<T extends LayoutManager> implements ILayout
{
	public UILayout()
	{
		
	}
	
	public abstract T createHandle(IParentComponent parent);
	
	@Override
	public boolean hasComponent(String pos)
	{
		return get(pos) != null;
	}
	
	@Override
	public void set(String pos, IComponent component)
	{
		add(component);
	}
	
	@Override
	public void apply(IParentComponent parent)
	{
		parent.getHandle().setLayout(createHandle(parent));
		
		update(parent);
	}
	
	public abstract void update(IParentComponent parent);
}
