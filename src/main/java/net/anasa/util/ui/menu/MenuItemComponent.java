package net.anasa.util.ui.menu;

import javax.swing.Icon;
import javax.swing.JMenuItem;

import net.anasa.util.ICallback;
import net.anasa.util.ui.ISwingComponent;
import net.anasa.util.ui.UIActionComponent;

public abstract class MenuItemComponent<T extends JMenuItem> extends UIActionComponent<T> implements ISwingComponent
{
	public MenuItemComponent(T handle)
	{
		super(handle);
	}
	
	@Override
	protected void setupActionCallback(ICallback callback)
	{
		getHandle().addActionListener((event) -> callback.call());
	}
	
	public String getText()
	{
		return getHandle().getText();
	}
	
	public void setText(String text)
	{
		getHandle().setText(text);
	}
	
	public Icon getIcon()
	{
		return getHandle().getIcon();
	}
	
	public void setIcon(Icon icon)
	{
		getHandle().setIcon(icon);
	}
}
