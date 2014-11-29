package net.anasa.util.ui;


public class OutputComponent extends TextAreaComponent
{
	public OutputComponent(int width, int height)
	{
		this();
		
		setColumns(width);
		setRows(height);
	}
	
	public OutputComponent()
	{
		super();
		
		setEditable(false);
		
		setWordWrap(true);
	}
	
	public String getText()
	{
		return getHandle().getText();
	}
	
	public void append(String message)
	{
		getHandle().append(message + '\n');
		getHandle().setCaretPosition(getText().length());
	}
}
