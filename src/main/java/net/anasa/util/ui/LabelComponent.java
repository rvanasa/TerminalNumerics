package net.anasa.util.ui;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;

public class LabelComponent extends UIParentComponent<JLabel> implements ISwingComponent
{
	public LabelComponent(Icon icon)
	{
		this(null, icon);
	}
	
	public LabelComponent(String text)
	{
		this(text, null);
	}
	
	public LabelComponent(String text, Icon icon)
	{
		super(new JLabel());
		
		setText(text);
		setIcon(icon);
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
	
	public Font getFont()
	{
		return getHandle().getFont();
	}
	
	public void setFont(Font font)
	{
		getHandle().setFont(font);
	}
}
