package net.anasa.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import net.anasa.util.Listing.IListCondition;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.DataConform.IConformHandler;

public class Mapping<K, V>
{
	private final HashMap<K, V> map = new LinkedHashMap<>();
	
	public Mapping()
	{
		
	}
	
	public HashMap<K, V> getMap()
	{
		return map;
	}
	
	public boolean hasKey(K key)
	{
		return getMap().containsKey(key);
	}
	
	public V get(K key)
	{
		return getMap().get(key);
	}
	
	public V getFirst(IListCondition<K> condition)
	{
		for(K key : getKeys())
		{
			if(condition.isValid(key))
			{
				return get(key);
			}
		}
		
		return null;
	}
	
	public V get(K key, V def)
	{
		return hasKey(key) ? getMap().get(key) : def;
	}
	
	public Mapping<K, V> put(K key, V value)
	{
		getMap().put(key, value);
		
		return this;
	}
	
	public boolean putIfNonexistant(K key, V value)
	{
		if(!hasKey(key))
		{
			put(key, value);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void remove(K key)
	{
		getMap().remove(key);
	}
	
	public Set<K> getKeys()
	{
		return getMap().keySet();
	}
	
	public Collection<V> getValues()
	{
		return getMap().values();
	}
	
	public boolean containsKey(K key)
	{
		return getMap().containsKey(key);
	}
	
	public boolean containsValue(V value)
	{
		return getMap().containsValue(value);
	}
	
	public int size()
	{
		return getMap().size();
	}
	
	@Override
	public String toString()
	{
		return getMap().toString();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return getMap().equals(obj);
	}
	
	public <T> Mapping<K, T> conform(IConformHandler<V, T> handler) throws FormatException
	{
		Mapping<K, T> map = new Mapping<>();
		
		for(K key : getKeys())
		{
			map.put(key, handler.getFrom(get(key)));
		}
		
		return map;
	}
	
	public Mapping<K, V> forEach(IMapAction<K, V> action)
	{
		for(K key : getKeys())
		{
			action.act(key, get(key));
		}
		
		return this;
	}
	
	public interface IMapAction<K, V>
	{
		public void act(K key, V value);
	}
}
