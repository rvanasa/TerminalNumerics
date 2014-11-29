package net.anasa.util.ui.menu;

import javax.swing.JMenu;

import net.anasa.util.ICallback;
import net.anasa.util.ui.UIActionComponent;

public class MenuComponent extends UIActionComponent<JMenu>
{
	public MenuComponent(String name, MenuItemComponent... items)
	{
		super(new JMenu(name));
		
		for(MenuItemComponent item : items)
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
	
	public void addItem(MenuItemComponent item)
	{
		getHandle().add(item.getHandle());
	}
}
