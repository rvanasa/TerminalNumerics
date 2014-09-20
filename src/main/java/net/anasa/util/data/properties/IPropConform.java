package net.anasa.util.data.properties;

import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.DataConform.IConformHandler;

public interface IPropConform<T> extends IConformHandler<Properties, T>
{
	@Override
	public T getFrom(Properties props) throws FormatException;
	
	public void format(Properties props, T data) throws FormatException;
	
	public default Properties getFormatted(T data) throws FormatException
	{
		Properties props = new Properties();
		format(props, data);
		
		return props;
	}
	
	public default IConformHandler<T, Properties> reverse()
	{
		return new IConformHandler<T, Properties>()
		{
			@Override
			public Properties getFrom(T data) throws FormatException
			{
				return getFormatted(data);
			}
		};
	}
}
