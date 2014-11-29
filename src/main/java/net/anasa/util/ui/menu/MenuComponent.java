package net.anasa.util.ui.menu;

import javax.swing.JMenu;

import net.anasa.util.ICallback;
import net.anasa.util.ui.IComponent;

public class MenuComponent extends MenuItemComponent<JMenu>
{
	public MenuComponent(String name, IComponent... items)
	{
		super(new JMenu(name));
		
		for(IComponent item : items)
		{
			addItem(item);
		}
	}

	@Override
	protected void setupActionCallback(ICallback callback)
	{
		getHandle().addActionListener((event) -> onAction());
	}
	
	public String getName()
	{
		return getHandle().getName();
	}
	
	public void setName(String name)
	{
		getHandle().setName(name);
	}
	
	public void addItem(IComponent item)
	{
		getHandle().add(item.getHandle());
	}
}
