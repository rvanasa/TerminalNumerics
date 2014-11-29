package net.anasa.util.ui;

import java.awt.event.KeyAdapter;

import javax.swing.JTextArea;

import net.anasa.util.ICallback;
import net.anasa.util.ui.event.IUIListener;
import net.anasa.util.ui.event.KeyEvent;

public class TextAreaComponent extends UIActionComponent<JTextArea> implements IInputComponent<String>, ISwingComponent
{
	public TextAreaComponent(String value)
	{
		this();
		
		setValue(value);
		setCaretPosition(0);
	}
	
	public TextAreaComponent(int width, int height)
	{
		this();
		
		setColumns(width);
		setRows(height);
	}
	
	public TextAreaComponent()
	{
		super(new JTextArea());
		
		getHandle().setWrapStyleWord(true);
		
		getHandle().addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(java.awt.event.KeyEvent event)
			{
				getEvents().dispatch(new KeyEvent(TextAreaComponent.this, event.getKeyChar()));
			}
		});
	}
	
	@Override
	protected void setupActionCallback(ICallback callback)
	{
		
	}
	
	public void addKeyListener(IUIListener<KeyEvent> listener)
	{
		getEvents().register(KeyEvent.class, listener);
	}
	
	@Override
	public String getValue()
	{
		return getHandle().getText();
	}
	
	@Override
	public void setValue(String value)
	{
		getHandle().setText(value);
	}
	
	public void append(String value)
	{
		getHandle().append(value);
		setCaretAtEnd();
	}
	
	public void appendLine(String line)
	{
		if(getValue() != null && !getValue().isEmpty())
		{
			append("\n");
		}
		
		append(line);
	}
	
	public int getRows()
	{
		return getHandle().getRows();
	}
	
	public void setRows(int rows)
	{
		getHandle().setRows(rows);
	}
	
	public int getColumns()
	{
		return getHandle().getColumns();
	}
	
	public void setColumns(int columns)
	{
		getHandle().setColumns(columns);
	}
	
	public boolean getWordWrap()
	{
		return getHandle().getLineWrap();
	}
	
	public void setWordWrap(boolean wrap)
	{
		getHandle().setLineWrap(wrap);
	}
	
	public boolean isEditable()
	{
		return getHandle().isEditable();
	}
	
	public void setEditable(boolean editable)
	{
		getHandle().setEditable(editable);
	}
	
	public int getCaretPosition()
	{
		return getHandle().getCaretPosition();
	}
	
	public void setCaretPosition(int position)
	{
		getHandle().setCaretPosition(position);
	}
	
	public void setCaretAtStart()
	{
		getHandle().setCaretPosition(0);
	}
	
	public void setCaretAtEnd()
	{
		getHandle().setCaretPosition(getValue().length());
	}
}