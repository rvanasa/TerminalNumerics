package net.anasa.math.ui;

import java.awt.Color;

public class FunctionColor
{
	private final String name;
	private final Color color;
	
	public FunctionColor(String name, int color)
	{
		this.name = name;
		this.color = new Color(color);
	}

	public String getName()
	{
		return name;
	}

	public Color getColor()
	{
		return color;
	}
}
