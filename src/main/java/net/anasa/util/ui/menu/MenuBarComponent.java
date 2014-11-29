package net.anasa.util.ui.menu;

import javax.swing.JMenuBar;

import net.anasa.util.ui.ISwingComponent;
import net.anasa.util.ui.UIComponent;

public class MenuBarComponent extends UIComponent<JMenuBar> implements ISwingComponent
{
	public MenuBarComponent(MenuComponent... menus)
	{
		super(new JMenuBar());
		
		for(MenuComponent menu : menus)
		{
			addMenu(menu);
		}
	}
	
	public void addMenu(MenuComponent menu)
	{
		getHandle().add(menu.getHandle());
	}
}
