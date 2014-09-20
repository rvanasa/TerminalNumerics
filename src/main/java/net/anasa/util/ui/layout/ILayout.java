package net.anasa.util.ui.layout;

import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.IParentComponent;

public interface ILayout
{
	public boolean hasComponent(String pos);
	
	public IComponent get(String pos);
	
	public void add(IComponent component);
	
	public void set(String pos, IComponent component);
	
	public void apply(IParentComponent component);
}
