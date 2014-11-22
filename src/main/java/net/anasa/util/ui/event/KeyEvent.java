package net.anasa.util.ui.event;

import net.anasa.util.ui.IComponent;

public class KeyEvent extends UIEvent
{
	private final char key;
	
	public KeyEvent(IComponent component, char key)
	{
		super(component);
		
		this.key = key;
	}
	
	public char getKey()
	{
		return key;
	}
}
