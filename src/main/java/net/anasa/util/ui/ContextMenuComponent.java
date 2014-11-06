package net.anasa.util.ui;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import net.anasa.util.ICallback;
import net.anasa.util.math.Vector2;

public class ContextMenuComponent extends UIParentComponent<JPopupMenu>
{
	public ContextMenuComponent(MenuOption... options)
	{
		super(new JPopupMenu());
		
		for(MenuOption option : options)
		{
			addMenuOption(option);
		}
	}
	
	public void addMenuOption(MenuOption option)
	{
		JMenuItem item = new JMenuItem(option.getName());
		item.addActionListener((event) -> option.getAction().call());
		
		getHandle().add(item);
	}
	
	public String getLabel()
	{
		return getHandle().getLabel();
	}
	
	public void setLabel(String label)
	{
		getHandle().setLabel(label);
	}
	
	public void display(IComponent component, Vector2 pos)
	{
		getHandle().show(component.getHandle(), (int)pos.getX(), (int)pos.getY());
	}
	
	public static class MenuOption
	{
		private final String name;
		private final ICallback action;
		
		public MenuOption(String name, ICallback action)
		{
			this.name = name;
			this.action = action;
		}

		public String getName()
		{
			return name;
		}

		public ICallback getAction()
		{
			return action;
		}
	}
}
