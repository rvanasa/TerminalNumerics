package net.anasa.util.data.properties;

import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.format.IFormat;

public class PropFormat<T> implements IFormat<T>
{
	private final String linefeed;
	private final IPropConform<T> handler;
	
	public PropFormat(IPropConform<T> handler)
	{
		this(";", handler);
	}
	
	public PropFormat(String linefeed, IPropConform<T> handler)
	{
		this.linefeed = linefeed;
		this.handler = handler;
	}
	
	public String getLinefeed()
	{
		return linefeed;
	}
	
	public IPropConform<T> getHandler()
	{
		return handler;
	}
	
	@Override
	public T getFrom(String data) throws FormatException
	{
		Properties props = new Properties();
		props.parse(data, getLinefeed());
		
		return getHandler().getFrom(props);
	}
	
	@Override
	public String getFormatted(T data) throws FormatException
	{
		Properties props = getHandler().getFormatted(data);
		
		return props.getFormatted(getLinefeed());
	}
}
