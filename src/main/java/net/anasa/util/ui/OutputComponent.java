package net.anasa.util.ui;

import javax.swing.JTextArea;

public class OutputComponent extends UIComponent<JTextArea> implements ISwingComponent
{
	public OutputComponent(int width, int height)
	{
		this();
		
		setColumns(width);
		setRows(height);
	}
	
	public OutputComponent()
	{
		super(new JTextArea());
		
		getHandle().setEditable(false);
		
		setWordWrap(true);
		getHandle().setWrapStyleWord(true);
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
	
	public int getColumns()
	{
		return getHandle().getColumns();
	}
	
	public void setColumns(int columns)
	{
		getHandle().setColumns(columns);
	}
	
	public int getRows()
	{
		return getHandle().getRows();
	}
	
	public void setRows(int rows)
	{
		getHandle().setRows(rows);
	}
	
	public boolean getWordWrap()
	{
		return getHandle().getLineWrap();
	}
	
	public void setWordWrap(boolean wrap)
	{
		getHandle().setLineWrap(wrap);
	}
}
