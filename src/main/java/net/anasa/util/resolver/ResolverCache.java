package net.anasa.util.resolver;

import java.util.HashMap;
import java.util.Map;

import net.anasa.util.Listing;
import net.anasa.util.resolver.ResolverCache.ICacheEntry;

public class ResolverCache<I extends ICacheEntry<I>, T>
{
	private final Map<Listing<I>, T> map = new HashMap<>();
	
	public Map<Listing<I>, T> getMap()
	{
		return map;
	}
	
	public boolean isCached(Listing<I> data)
	{
		return get(data) != null;
	}
	
	public T get(Listing<I> data)
	{
		loop: for(Listing<I> list : getMap().keySet())
		{
			if(list.size() == data.size())
			{
				for(int i = 0; i < list.size(); i++)
				{
					if(!data.get(i).isEquivalent(list.get(i)))
					{
						continue loop;
					}
				}
				
				return getMap().get(list);
			}
		}
		
		return null;
	}
	
	public T cache(Listing<I> data, T value)
	{
		getMap().putIfAbsent(data, value);
		return value;
	}
	
	public interface ICacheEntry<I>
	{
		public boolean isEquivalent(I other);
	}
}
