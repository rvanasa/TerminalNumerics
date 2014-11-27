package net.anasa.util.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JLabel;

public class LabelComponent extends UIParentComponent<JLabel> implements ISwingComponent
{
	public LabelComponent(String text)
	{
		super(new JLabel(text));
		
		getHandle().addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent event)
			{
				if(getHandle().getParent() != null)
				{
					getHandle().getParent().dispatchEvent(event);
				}
			}
		});
	}
	
	public LabelComponent()
	{
		this("");
	}
	
	public LabelComponent(Icon icon, String text)
	{
		this(text);
		
		setIcon(icon);
	}
	
	public LabelComponent(Icon icon)
	{
		this(icon, null);
	}
	
	public LabelComponent(String text, Color color)
	{
		this(text);
		
		setForeground(color);
	}
	
	public LabelComponent(String text, Font font)
	{
		this(text);
		
		setFont(font);
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
