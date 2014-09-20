package net.anasa.util.ui.layout;

import java.awt.FlowLayout;

import net.anasa.util.Listing;
import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.IParentComponent;

public class UIFlowLayout extends UILayout<FlowLayout>
{
	private final Listing<IComponent> components = new Listing<>();
	
	private FlowType type;
	
	public UIFlowLayout()
	{
		this(FlowType.CENTER);
	}
	
	public UIFlowLayout(FlowType type)
	{
		this.type = type;
	}

	public Listing<IComponent> getComponents()
	{
		return components;
	}

	public FlowType getType()
	{
		return type;
	}

	public void setType(FlowType type)
	{
		this.type = type;
	}

	@Override
	public FlowLayout createHandle(IParentComponent parent)
	{
		return new FlowLayout(getType().getValue());
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
	public void update(IParentComponent parent)
	{
		for(IComponent component : getComponents())
		{
			parent.addComponent(component);
		}
	}
	
	public enum FlowType
	{
		LEFT(FlowLayout.LEFT),
		RIGHT(FlowLayout.RIGHT),
		CENTER(FlowLayout.CENTER);
		
		private final int value;
		
		private FlowType(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return value;
		}
	}
}
