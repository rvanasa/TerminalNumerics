package net.anasa.util.ui;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.TitledBorder;

public interface ISwingComponent extends IComponent
{
	@Override
	public JComponent getHandle();
	
	public default void setBorder(int x, int y)
	{
		getHandle().setBorder(BorderFactory.createEmptyBorder(y, x, y, x));
	}
	
	public default void setBorder(String text)
	{
		getHandle().setBorder(BorderFactory.createTitledBorder(null, text, TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.DIALOG, Font.PLAIN, 11)));
	}
	
	public default void setToolTipText(String text)
	{
		getHandle().setToolTipText(text);
	}
	
	public default void setVisible(boolean visible)
	{
		getHandle().setVisible(visible);
	}
}
