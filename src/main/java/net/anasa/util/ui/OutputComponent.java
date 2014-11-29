package net.anasa.util.ui;

import net.anasa.util.Debug;

public class OutputComponent extends TextAreaComponent
{
	public OutputComponent(int width, int height)
	{
		this();
		
		setColumns(width);
		setRows(height);
		
		Debug.registerListener((type, message) -> appendLine("[" + type + "] " + message));
		Debug.log("Logging system output");
	}
	
	public OutputComponent()
	{
		super();
		
		setEditable(false);
	}
}
