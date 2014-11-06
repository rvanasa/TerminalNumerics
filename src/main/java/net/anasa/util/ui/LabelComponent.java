package net.anasa.util.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;

public class LabelComponent extends UIParentComponent<JLabel> implements ISwingComponent
{
	public LabelComponent(Icon icon, String text)
	{
		super(new JLabel());
		
		setIcon(icon);
		setText(text);
	}
	
	public LabelComponent(Icon icon)
	{
		this(icon, null);
	}
	
	public LabelComponent(String text)
	{
		super(new JLabel(text));
		
		setText(text);
	}
	
	public LabelComponent(String text, Color color)
	{
		this(text);
		
		setForeground(color);
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
