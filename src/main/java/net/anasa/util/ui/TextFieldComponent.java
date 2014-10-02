package net.anasa.util.ui;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.anasa.util.ICallback;

public class TextFieldComponent extends UIActionComponent<JTextField> implements IInputComponent<String>, ISwingComponent
{
	public TextFieldComponent()
	{
		this(null);
	}
	
	public TextFieldComponent(ITextCallback callback)
	{
		this(20, callback);
	}
	
	public TextFieldComponent(int width)
	{
		this(width, null);
	}
	
	public TextFieldComponent(int width, ITextCallback callback)
	{
		this(width, callback, null);
	}
	
	public TextFieldComponent(int width, ITextCallback callback, String value)
	{
		this(width, callback, value, false);
	}
	
	public TextFieldComponent(int width, ITextCallback callback, String value, boolean password)
	{
		super(password ? new JPasswordField(width) : new JTextField(width));
		
		if(callback != null)
		{
			addActionListener(() -> callback.call(getValue()));
		}
		
		setValue(value);
	}
	
	@Override
	protected void setupActionCallback(ICallback callback)
	{
		getHandle().addActionListener((event) -> callback.call());
	}
	
	public int getColumns()
	{
		return getHandle().getColumns();
	}
	
	public void setColumns(int columns)
	{
		getHandle().setColumns(columns);
	}
	
	@Override
	public String getValue()
	{
		return getHandle().getText();
	}
	
	@Override
	public void setValue(String text)
	{
		getHandle().setText(text);
		getHandle().setCaretPosition(0);
	}
	
	public boolean isEditable()
	{
		return getHandle().isEditable();
	}
	
	public void setEditable(boolean editable)
	{
		getHandle().setEditable(editable);
	}
	
	public interface ITextCallback
	{
		public void call(String data);
	}
}
