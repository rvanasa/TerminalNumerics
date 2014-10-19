package net.anasa.util.data.properties;

import net.anasa.util.Pair;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.DataConform.IConformHandler;

public class KVPair implements Pair<String, String>
{
	private final String key, value;
	
	public <T> KVPair(String key, T value, IConformHandler<T, String> conform) throws FormatException
	{
		this(key, conform.getFrom(value));
	}
	
	public KVPair(String key, Object value)
	{
		this(key, String.valueOf(value));
	}
	
	public KVPair(String key, String value)
	{
		this.key = key;
		this.value = value;
	}
	
	@Override
	public String getKey()
	{
		return key;
	}
	
	@Override
	public String getValue()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return key + AbstractProperties.SPLITTER + " " + value;
	}
}