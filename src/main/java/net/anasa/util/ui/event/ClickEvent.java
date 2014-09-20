package net.anasa.util.ui.event;

import net.anasa.util.ui.IComponent;

public class ClickEvent extends UIEvent
{
	private final ButtonType button;
	
	public ClickEvent(IComponent component, ButtonType button)
	{
		super(component);
		
		this.button = button;
	}
	
	public ButtonType getButton()
	{
		return button;
	}
	
	public enum ButtonType
	{
		NONE,
		LEFT,
		MIDDLE,
		RIGHT,
		OTHER;
		
		public static ButtonType get(int id)
		{
			switch(id)
			{
			case 0:
				return NONE;
			case 1:
				return LEFT;
			case 2:
				return RIGHT;
			case 3:
				return MIDDLE;
			default:
				return OTHER;
			}
		}
	}
}
