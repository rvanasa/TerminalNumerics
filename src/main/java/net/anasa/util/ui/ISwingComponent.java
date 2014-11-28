package net.anasa.util.ui;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

interface ISwingComponent extends IComponent
{
	@Override
	public JComponent getHandle();
	
	default Font getFont()
	{
		return getHandle().getFont();
	}
	
	default void setFont(Font font)
	{
		getHandle().setFont(font);
	}
	
	default void setBorder(int x, int y)
	{
		setBorder(BorderFactory.createEmptyBorder(y, x, y, x));
	}
	
	default void setBorder(String text)
	{
		setBorder(text, new Font(Font.DIALOG, Font.PLAIN, 11));
	}
	
	default void setBorder(String text, Font font)
	{
		setBorder(BorderFactory.createTitledBorder(null, text, TitledBorder.CENTER, TitledBorder.TOP, font));
	}
	
	default void setBorder(Border border)
	{
		getHandle().setBorder(border);
	}
	
	default void removeBorder()
	{
		setBorder(BorderFactory.createEmptyBorder());
	}
	
	default void setToolTipText(String text)
	{
		getHandle().setToolTipText(text);
	}
	
	default void setVisible(boolean visible)
	{
		getHandle().setVisible(visible);
	}
}
