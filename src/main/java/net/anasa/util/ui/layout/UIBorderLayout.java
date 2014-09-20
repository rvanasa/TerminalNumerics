package net.anasa.util.ui.layout;

import java.awt.BorderLayout;

import net.anasa.util.Mapping;
import net.anasa.util.StringHelper;
import net.anasa.util.ui.IComponent;
import net.anasa.util.ui.IParentComponent;

public class UIBorderLayout extends UILayout<BorderLayout>
{
	private final Mapping<BorderPosition, IComponent> positions = new Mapping<>();
	
	private int padding;
	
	public UIBorderLayout()
	{
		this(0);
	}
	
	public UIBorderLayout(int padding)
	{
		setPadding(padding);
	}
	
	@Override
	public BorderLayout createHandle(IParentComponent parent)
	{
		BorderLayout layout = new BorderLayout();
		
		layout.setHgap(getPadding());
		layout.setVgap(getPadding());
		
		return layout;
	}
	
	public Mapping<BorderPosition, IComponent> getPositions()
	{
		return positions;
	}
	
	public int getPadding()
	{
		return padding;
	}
	
	public void setPadding(int padding)
	{
		this.padding = padding;
	}
	
	@Override
	public IComponent get(String pos)
	{
		return get(BorderPosition.valueOf(StringHelper.upperCase(pos)));
	}
	
	public IComponent get(BorderPosition pos)
	{
		return getPositions().get(pos);
	}
	
	@Override
	public void add(IComponent component)
	{
		set(BorderPosition.CENTER, component);
	}
	
	@Override
	public void set(String pos, IComponent component)
	{
		set(BorderPosition.valueOf(StringHelper.upperCase(pos)), component);
	}
	
	public void set(BorderPosition pos, IComponent component)
	{
		getPositions().put(pos == null ? BorderPosition.CENTER : pos, component);
	}
	
	@Override
	public void update(IParentComponent parent)
	{
		getPositions().forEach((pos, component) -> parent.setComponent(component, pos.getID()));
	}
	
	public enum BorderPosition
	{
		CENTER(BorderLayout.CENTER),
		TOP(BorderLayout.NORTH),
		BOTTOM(BorderLayout.SOUTH),
		LEFT(BorderLayout.WEST),
		RIGHT(BorderLayout.EAST);
		
		private final String id;
		
		private BorderPosition(String id)
		{
			this.id = id;
		}
		
		public String getID()
		{
			return id;
		}
		
		public static BorderPosition getByID(String pos)
		{
			for(BorderPosition position : values())
			{
				if(position.getID().equalsIgnoreCase(pos))
				{
					return position;
				}
			}
			
			return null;
		}
	}
}
