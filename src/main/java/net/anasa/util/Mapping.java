package net.anasa.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.anasa.util.Listing.IListCondition;
import net.anasa.util.data.DataConform.FormatException;
import net.anasa.util.data.DataConform.IConformHandler;

public class Mapping<K, V> implements Iterable<Pair<K, V>>
{
	private final HashMap<K, V> map = new LinkedHashMap<>();
	
	public Mapping()
	{
		
	}
	
	public Map<K, V> getMap()
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
	
	public V getFirst(IMapCondition<K, V> condition)
	{
		for(K key : getKeys())
		{
			V value = get(key);
			
			if(condition.isValid(key, value))
			{
				return value;
			}
		}
		
		return null;
	}
	
	public K getFirstKey(IListCondition<K> condition)
	{
		for(K key : getKeys())
		{
			if(condition.isValid(key))
			{
				return key;
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
	
	public void clear()
	{
		getMap().clear();
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
	
	public Listing<Pair<K, V>> getEntries()
	{
		return new Listing<>(getMap().entrySet()).conform((entry) -> new EntryPair(entry));
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

	@Override
	public Iterator<Pair<K, V>> iterator()
	{
		return getEntries().iterator();
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
		for(Pair<K, V> entry : getEntries())
		{
			action.act(entry.getKey(), entry.getValue());
		}
		
		return this;
	}
	
	private class EntryPair implements Pair<K, V>
	{
		private final Entry<K, V> entry;
		
		public EntryPair(Entry<K, V> entry)
		{
			this.entry = entry;
		}
		
		@Override
		public K getKey()
		{
			return entry.getKey();
		}

		@Override
		public V getValue()
		{
			return entry.getValue();
		}
	}
	
	public interface IMapCondition<K, V>
	{
		public boolean isValid(K key, V value);
	}
	
	public interface IMapAction<K, V>
	{
		public void act(K key, V value);
	}
}
