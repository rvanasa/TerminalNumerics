package net.anasa.util.ui.menu;

import javax.swing.Icon;
import javax.swing.JMenuItem;

import net.anasa.util.ICallback;
import net.anasa.util.ui.IInputComponent;
import net.anasa.util.ui.ISwingComponent;
import net.anasa.util.ui.UIActionComponent;

public class MenuItemComponent extends UIActionComponent<JMenuItem> implements IInputComponent<Boolean>, ISwingComponent
{
	public MenuItemComponent(String text, ICallback callback)
	{
		this(text, (item) -> callback.call());
	}
	
	public MenuItemComponent(String text, IMenuItemCallback callback)
	{
		super(new JMenuItem(text));
		
		addActionListener(callback);
	}
	
	@Override
	protected void setupActionCallback(ICallback callback)
	{
		getHandle().addActionListener((event) -> callback.call());
	}
	
	public void addActionListener(IMenuItemCallback callback)
	{
		addActionListener(() -> callback.call(this));
	}
	
	public String getText()
	{
		return getHandle().getText();
	}
	
	public void setText(String text)
	{
		getHandle().setText(text);
	}

	@Override
	public Boolean getValue()
	{
		return getHandle().isSelected();
	}

	@Override
	public void setValue(Boolean value)
	{
		getHandle().setSelected(value);
	}
	
	public void toggle()
	{
		setValue(!getValue());
	}
	
	public Icon getIcon()
	{
		return getHandle().getIcon();
	}
	
	public void setIcon(Icon icon)
	{
		getHandle().setIcon(icon);
	}
	
	public interface IMenuItemCallback
	{
		public void call(MenuItemComponent item);
	}
}
