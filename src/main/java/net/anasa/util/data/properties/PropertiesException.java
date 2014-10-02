package net.anasa.util.data.properties;

import net.anasa.util.data.DataConform.FormatException;

public class PropertiesException extends FormatException
{
	public PropertiesException(String message)
	{
		super(message);
	}
	
	public PropertiesException(Throwable e)
	{
		super(e);
	}
	
	public PropertiesException(String message, Throwable e)
	{
		super(message, e);
	}
}