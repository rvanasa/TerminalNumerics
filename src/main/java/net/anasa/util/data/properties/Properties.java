package net.anasa.util.data.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.format.IDataFormat;

public class Properties extends AbstractProperties
{
	public static final IDataFormat<Properties> FORMAT = new IDataFormat<Properties>()
	{
		@Override
		public Properties getFrom(String data) throws FormatException
		{
			Properties props = new Properties();
			props.parse(data);
			
			return props;
		}
		
		@Override
		public String getFormatted(Properties data) throws FormatException
		{
			return data.getFormatted();
		}
	};
	
	public static Properties getFrom(String data) throws FormatException
	{
		Properties props = new Properties();
		props.parse(data);
		
		return props;
	}
	
	public static Properties getFrom(InputStream stream) throws IOException
	{
		Properties props = new Properties();
		props.read(stream);
		
		return props;
	}
	
	public static Properties getFrom(InputStream stream, Properties def)
	{
		try
		{
			return getFrom(stream);
		}
		catch(IOException e)
		{
			return def;
		}
	}
	
	public static Properties getFrom(String data, Properties def)
	{
		try
		{
			return getFrom(data);
		}
		catch(FormatException e)
		{
			return def;
		}
	}
	
	private final HashMap<String, String> map = new LinkedHashMap<>();
	
	public Properties(HashMap<String, String> map)
	{
		this.map.putAll(map);
	}
	
	public Properties()
	{
		
	}
	
	public Properties(KVPair... pairs)
	{
		for(KVPair pair : pairs)
		{
			set(pair);
		}
	}

	@Override
	protected String getValue(String key)
	{
		return map.get(key);
	}

	@Override
	protected void putValue(String key, String value)
	{
		map.put(key, value);
	}
	
	@Override
	public void remove(String key)
	{
		map.remove(key);
	}

	@Override
	public Collection<String> getKeys()
	{
		return map.keySet();
	}

	@Override
	public Collection<String> getValues()
	{
		return map.values();
	}
	
	@Override
	public String toString()
	{
		return map.toString();
	}
}
