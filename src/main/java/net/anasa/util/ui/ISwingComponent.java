package net.anasa.util.ui;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

public interface ISwingComponent
{
	public JComponent getHandle();
	
	public default void setBorder(int x, int y)
	{
		getHandle().setBorder(BorderFactory.createEmptyBorder(y, x, y, x));
	}
	
	public default void setToolTipText(String text)
	{
		getHandle().setToolTipText(text);
	}
}
