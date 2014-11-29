package net.anasa.util.ui.menu;

import javax.swing.JMenuItem;

import net.anasa.util.ICallback;

public class MenuActionComponent extends MenuItemComponent<JMenuItem>
{
	public MenuActionComponent(String text, ICallback callback)
	{
		super(new JMenuItem(text));
		
		addActionListener(callback);
	}
}
