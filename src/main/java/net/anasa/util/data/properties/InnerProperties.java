package net.anasa.util.data.properties;

import java.util.Collection;
import java.util.HashMap;

public class InnerProperties extends Properties
{
	private final AbstractProperties parent;
	private final String id;
	
	public InnerProperties(AbstractProperties parent, String id)
	{
		this.parent = parent;
		this.id = id;
	}
	
	public AbstractProperties getParent()
	{
		return parent;
	}

	public String getID()
	{
		return id;
	}
	
	private HashMap<String, String> compileMap()
	{
		HashMap<String, String> map = new HashMap<>();
		
		for(KVPair kv : getParent())
		{
			if(kv.getKey().startsWith(getID() + DOT))
			{
				map.put(kv.getKey().substring((getID() + DOT).length()), kv.getValue());
			}
		}
		
		return map;
	}
	
	@Override
	protected String getValue(String key)
	{
		return getParent().getValue(getID() + DOT + key);
	}
	
	@Override
	protected void putValue(String key, String value)
	{
		getParent().putValue(getID() + DOT + key, value);
	}
	
	@Override
	public void remove(String key)
	{
		getParent().remove(getID() + DOT + key);
	}
	
	@Override
	public Collection<String> getKeys()
	{
		return compileMap().keySet();
	}
	
	@Override
	public Collection<String> getValues()
	{
		return compileMap().values();
	}
	
	@Override
	public Properties getInner(String id)
	{
		return getParent().getInner(getID() + DOT + id);
	}
	
	@Override
	public String toString()
	{
		return getID() + "::" + compileMap().toString();
	}
}