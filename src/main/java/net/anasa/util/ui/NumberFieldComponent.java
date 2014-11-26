package net.anasa.util.ui;

import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;

import net.anasa.util.ICallback;

public abstract class NumberFieldComponent<T> extends UIActionComponent<JSpinner> implements IInputComponent<T>, ISwingComponent
{
	public NumberFieldComponent(SpinnerModel model)
	{
		super(new JSpinner(model));
	}
	
	@Override
	protected void setupActionCallback(ICallback callback)
	{
		getHandle().addInputMethodListener(new InputMethodListener()
		{
			@Override
			public void inputMethodTextChanged(InputMethodEvent event)
			{
				callback.call();
			}
			
			@Override
			public void caretPositionChanged(InputMethodEvent event)
			{
			}
		});
	}
	
	public SpinnerModel getModel()
	{
		return getHandle().getModel();
	}
	
	public void setWidth(int width)
	{
		setSize(width, 20);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T getValue()
	{
		try
		{
			return (T)getHandle().getValue();
		}
		catch(ClassCastException e)
		{
			e.printStackTrace();
			
			return null;
		}
	}
	
	@Override
	public void setValue(T value)
	{
		if(value == null)
		{
			return;
		}
		
		getHandle().setValue(value);
	}
}
