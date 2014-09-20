package net.anasa.util.data.properties;

import net.anasa.util.Debug;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.format.IDataFormat;
import net.anasa.util.data.properties.AbstractProperties.PropertiesException;

public class PropertyNode<T>
{
	private final Properties props;
	
	private final String key;
	private final T def;
	private final IDataFormat<T> format;
	
	public PropertyNode(Properties props, String key, IDataFormat<T> format)
	{
		this(props, key, null, format);
	}
	
	public PropertyNode(Properties props, String key, T def, IDataFormat<T> format)
	{
		this.props = props;
		
		this.key = key;
		this.def = def;
		this.format = format;
	}
	
	public Properties getProps()
	{
		return props;
	}
	
	public IDataFormat<T> getFormat()
	{
		return format;
	}
	
	public String getKey()
	{
		return key;
	}
	
	public T getDefault()
	{
		return def;
	}
	
	public T getValue()
	{
		try
		{
			return getFormat().getFrom(getProps().getString(getKey()));
		}
		catch(PropertiesException e)
		{
			
		}
		catch(FormatException e)
		{
			e.printStackTrace();
		}
		
		setValue(getDefault());
		
		return getDefault();
	}
	
	public boolean setValue(T value)
	{
		try
		{
			getProps().set(getKey(), getFormat().getFormatted(value));
			return true;
		}
		catch(FormatException e)
		{
			Debug.err("Failed to set property node value: " + value);
			e.printStackTrace();
			return false;
		}
	}
}