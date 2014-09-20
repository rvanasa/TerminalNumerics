package net.anasa.util.ui.layout;

import javax.swing.Box;
import javax.swing.BoxLayout;

import net.anasa.util.Listing;
import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.IParentComponent;

public class UIVerticalLayout extends UILayout<BoxLayout>
{
	private final Listing<IComponent> components = new Listing<>();
	
	private int spacing;
	
	public UIVerticalLayout()
	{
		this(4);
	}
	
	public UIVerticalLayout(int spacing)
	{
		setSpacing(spacing);
	}

	public Listing<IComponent> getComponents()
	{
		return components;
	}

	public int getSpacing()
	{
		return spacing;
	}

	public void setSpacing(int spacing)
	{
		this.spacing = spacing;
	}
	
	@Override
	public void add(IComponent component)
	{
		getComponents().add(component);
	}
	
	@Override
	public IComponent get(String pos)
	{
		return null;
	}

	@Override
	public BoxLayout createHandle(IParentComponent parent)
	{
		return new BoxLayout(parent.getHandle(), BoxLayout.PAGE_AXIS);
	}
	
	@Override
	public void update(IParentComponent parent)
	{
		for(IComponent component : getComponents())
		{
			parent.addComponent(component);
			parent.addComponent(() -> Box.createVerticalStrut(getSpacing()));
		}
	}
}
