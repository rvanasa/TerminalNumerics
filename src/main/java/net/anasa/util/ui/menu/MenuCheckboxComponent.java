package net.anasa.util.ui.menu;

import javax.swing.JCheckBoxMenuItem;

import net.anasa.util.ui.IInputComponent;

public class MenuCheckboxComponent extends MenuItemComponent<JCheckBoxMenuItem> implements IInputComponent<Boolean>
{
	public MenuCheckboxComponent(String text)
	{
		this(text, false);
	}
	
	public MenuCheckboxComponent(String text, boolean value)
	{
		super(new JCheckBoxMenuItem(text));
		
		setValue(value);
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
}
