package net.anasa.util.data.properties;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import net.anasa.util.Checks;
import net.anasa.util.Debug;
import net.anasa.util.Listing;
import net.anasa.util.Mapping;
import net.anasa.util.NumberHelper;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.DataConform.IConformHandler;
import net.anasa.util.data.format.IFormat;
import net.anasa.util.data.io.IOHelper;

abstract class AbstractProperties implements Iterable<KVPair>
{
	protected static final String DOT = ".";
	
	protected static final String SPLITTER = ":";
	protected static final String COMMENT = "#";
	
	protected abstract String getValue(String key);
	
	protected abstract void putValue(String key, String value);
	
	public abstract void remove(String key);
	
	public abstract Collection<String> getKeys();
	
	public abstract Collection<String> getValues();
	
	@Override
	public abstract String toString();
	
	public KVPair[] getKVPairs()
	{
		Listing<KVPair> list = new Listing<>();
		
		for(String key : getKeys())
		{
			list.add(new KVPair(key, getValue(key)));
		}
		
		return list.toArray(KVPair.class);
	}
	
	public boolean hasInner(String key)
	{
		for(String parse : getKeys())
		{
			if(parse.startsWith(key + DOT))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public Properties getInner(String id)
	{
		return new InnerProperties(this, id);
	}
	
	public Mapping<String, Properties> getInnerProps()
	{
		Mapping<String, Properties> map = new Mapping<>();
		
		for(String key : getKeys())
		{
			if(key.contains(DOT))
			{
				String id = key.substring(0, key.indexOf(DOT));
				
				if(!map.containsKey(id))
				{
					map.put(id, getInner(id));
				}
			}
		}
		
		return map;
	}
	
	public Listing<Properties> getInnerList(String key)
	{
		Listing<Properties> list = new Listing<>();
		
		getInner(key).getInnerProps().forEach((id, inner) -> {
			if(NumberHelper.isInteger(id))
			{
				int index = NumberHelper.getInteger(id);
				list.set(index, inner);
			}
				else
				{
					list.add(inner);
				}
			});
		
		return list;
	}
	
	public boolean hasKey(String key)
	{
		return getKeys().contains(format(key));
	}
	
	protected String get(String key) throws PropertiesException
	{
		if(key == null)
		{
			throw new PropertiesException("Key cannot be null");
		}
		
		if(hasKey(key))
		{
			return getValue(format(key));
		}
		else
		{
			throw new PropertiesException("Key does not exist: " + key);
		}
	}
	
	public <T> T get(String key, IConformHandler<String, T> conform) throws PropertiesException
	{
		try
		{
			return conform.getFrom(get(key));
		}
		catch(FormatException e)
		{
			throw new PropertiesException(e.getMessage());
		}
	}
	
	private String format(String key)
	{
		return key.toLowerCase();
	}
	
	public String getString(String key) throws PropertiesException
	{
		return get(key);
	}
	
	public String getString(String key, String def)
	{
		try
		{
			return getString(key);
		}
		catch(PropertiesException e)
		{
			return def;
		}
	}
	
	public int getInt(String key) throws PropertiesException
	{
		return get(key, IFormat.INT);
	}
	
	public int getInt(String key, int def)
	{
		try
		{
			return getInt(key);
		}
		catch(PropertiesException e)
		{
			return def;
		}
	}
	
	public double getDouble(String key) throws NumberFormatException, PropertiesException
	{
		return get(key, IFormat.DOUBLE);
	}
	
	public double getDouble(String key, double def)
	{
		try
		{
			return getDouble(key);
		}
		catch(PropertiesException e)
		{
			return def;
		}
	}
	
	public float getFloat(String key) throws NumberFormatException, PropertiesException
	{
		return get(key, IFormat.FLOAT);
	}
	
	public float getFloat(String key, float def)
	{
		try
		{
			return getFloat(key);
		}
		catch(PropertiesException e)
		{
			return def;
		}
	}
	
	public boolean getBoolean(String key) throws PropertiesException
	{
		return get(key, IFormat.BOOLEAN);
	}
	
	public boolean getBoolean(String key, boolean def)
	{
		try
		{
			return getBoolean(key);
		}
		catch(PropertiesException e)
		{
			return def;
		}
	}
	
	public String[] getArray(String key, String splitter) throws PropertiesException
	{
		return get(key, (data) -> data.isEmpty() ? new String[0] : data.split(splitter));
	}
	
	public String[] getArray(String key, String splitter, String[] def)
	{
		try
		{
			return getArray(key, splitter);
		}
		catch(PropertiesException e)
		{
			return def;
		}
	}
	
	public <T> boolean set(String key, T value, IConformHandler<T, String> conform) throws PropertiesException
	{
		try
		{
			return set(key, conform.getFrom(value));
		}
		catch(FormatException e)
		{
			throw new PropertiesException(e);
		}
	}
	
	public boolean set(String key, Object value)
	{
		if(key == null)
		{
			Debug.warn("Attempted to set object with null key: " + value);
			return false;
		}
		
		boolean ret = hasKey(key);
		
		putValue(format(key), String.valueOf(value));
		
		return ret;
	}
	
	public boolean set(KVPair kv)
	{
		return set(kv.getKey(), kv.getValue());
	}
	
	public boolean setIfNonexistant(KVPair kv)
	{
		return setDefault(kv.getKey(), kv.getValue());
	}
	
	public boolean setDefault(String key, Object value)
	{
		if(!hasKey(key))
		{
			set(key, value);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void setAll(Properties props)
	{
		if(props == null)
		{
			return;
		}
		
		for(KVPair pair : props)
		{
			set(pair);
		}
	}
	
	public void clear()
	{
		getKeys().clear();
	}
	
	public boolean tryRead(InputStream stream)
	{
		try
		{
			read(stream);
			return true;
		}
		catch(IOException e)
		{
			return false;
		}
	}
	
	public boolean tryWrite(OutputStream stream)
	{
		try
		{
			write(stream);
			return true;
		}
		catch(IOException e)
		{
			return false;
		}
	}
	
	public void read(InputStream stream) throws IOException
	{
		try
		{
			parse(IOHelper.read(stream));
		}
		catch(PropertiesException e)
		{
			throw new IOException(e);
		}
	}
	
	public void write(OutputStream stream) throws IOException
	{
		try
		{
			IOHelper.write(stream, getFormatted());
		}
		catch(PropertiesException e)
		{
			throw new IOException(e);
		}
	}
	
	public void parse(String data) throws PropertiesException
	{
		parse(data, "\n");
	}
	
	public void parse(String data, String linefeed) throws PropertiesException
	{
		Checks.checkNotNull(data, new PropertiesException("Cannot parse null string"));
		
		for(String line : data.split(linefeed))
		{
			if(!line.startsWith(COMMENT))
			{
				if(line.length() > 0)
				{
					if(line.contains(SPLITTER))
					{
						String key = line.substring(0, line.indexOf(SPLITTER)).trim();
						String value = line.substring(line.indexOf(SPLITTER) + SPLITTER.length()).trim();
						
						set(key, value);
					}
					else
					{
						throw new PropertiesException("Line does not contain splitter: " + SPLITTER);
					}
				}
			}
		}
	}
	
	public String getFormatted() throws PropertiesException
	{
		return getFormatted("\n");
	}
	
	public String getFormatted(String line) throws PropertiesException
	{
		String data = "";
		
		for(KVPair kv : getKVPairs())
		{
			data += kv.getKey() + SPLITTER + " " + kv.getValue() + line;
		}
		
		return data;
	}
	
	@Override
	public Iterator<KVPair> iterator()
	{
		return new ArrayList<>(Arrays.asList(getKVPairs())).iterator();
	}
}
