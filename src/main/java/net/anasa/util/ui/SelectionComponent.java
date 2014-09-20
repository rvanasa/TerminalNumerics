package net.anasa.util.ui;

import java.awt.Dimension;

import javax.swing.JComboBox;

import net.anasa.util.ICallback;
import net.anasa.util.Listing;
import net.anasa.util.StringHelper;

public class SelectionComponent<T> extends UIActionComponent<JComboBox<String>> implements IInputComponent<T>
{
	private final Listing<T> values = new Listing<>();
	
	private final IDisplayConform<T> displayConform;
	
	public SelectionComponent()
	{
		this((item) -> StringHelper.getOrNull(item));
	}
	
	public SelectionComponent(Listing<T> values, IDisplayConform<T> displayConform)
	{
		this(displayConform);
		
		setValues(values);
	}
	
	public SelectionComponent(IDisplayConform<T> displayConform)
	{
		super(new JComboBox<>());
		
		this.displayConform = displayConform;
		
		getHandle().setFocusable(false);
	}
	
	@Override
	protected void setupActionCallback(ICallback callback)
	{
		getHandle().addActionListener((event) -> callback.call());
	}
	
	public Listing<T> getValues()
	{
		return values;
	}
	
	public IDisplayConform<T> getDisplayConform()
	{
		return displayConform;
	}
	
	public void setWidth(int width)
	{
		getHandle().setPreferredSize(new Dimension(width, 20));
	}
	
	public void setValues(Listing<T> values)
	{
		clear();
		
		for(T item : values)
		{
			addValue(item);
		}
	}
	
	public void addValue(T value)
	{
		getValues().add(value);
		getHandle().addItem(getDisplayConform().getFrom(value));
	}
	
	public void removeValue(T value)
	{
		getValues().remove(value);
		getHandle().removeItem(value);
	}
	
	public void clear()
	{
		getValues().clear();
		getHandle().removeAllItems();
	}
	
	public int getIndex()
	{
		return getHandle().getSelectedIndex();
	}
	
	public void setIndex(int index)
	{
		getHandle().setSelectedIndex(index);
	}
	
	public T getValue(int index)
	{
		return getValues().get(index);
	}
	
	@Override
	public T getValue()
	{
		return getIndex() == -1 ? null : getValue(getIndex());
	}
	
	@Override
	public void setValue(T value)
	{
		getHandle().setSelectedItem(value);
	}
	
	public interface IDisplayConform<T>
	{
		public String getFrom(T value);
	}
}
